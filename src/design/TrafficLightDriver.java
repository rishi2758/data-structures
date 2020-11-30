package design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

//Designing a traffic light
//1. should display a waiting time for each lane
//2. manage coordination among the each lanes.
//3. anyTime waiting time could be changed.
//overall wait time for each lane
//60,30,15,15
//
enum TLState
{
    RED,
    GREEN,
    YELLOW,
    ORANGE;

    public TLState nextState()
    {
        TLState nextState = TLState.YELLOW;
        switch (this) {
            case GREEN:
                nextState = YELLOW;
                break;
            case ORANGE:
                nextState = RED;
                break;
            case RED:
                nextState = GREEN;
                break;
            case YELLOW:
                nextState = ORANGE;
                break;
        }
        return nextState;
    }
}

enum TLPosition
{
    FRONT,
    BACK,
    LEFT,
    RIGHT;

}

interface TLLight
{

    void register(ITrafficLight trafficLight);

    void deregister(ITrafficLight trafficLight);

    void notifyOthers(ITrafficLight trafficLight);

    void start();
}

interface ITrafficLight
{

    String getUuid();

    void display();

    TLState getCurrentState();

    TLPosition getPosition();

    void changeState();

}

class TLDisplay
{

    private boolean[] allowedLanes;

    public TLDisplay(boolean[] allowedLanes)
    {
        this.allowedLanes = allowedLanes;
    }

    public void display()
    {

        int i = 0;
        for (boolean lane : allowedLanes) {
            if (i == 4) {
                break;
            }
            if (lane) {
                switch (i) {
                    case 0:
                        System.out.println("LEFT");
                        break;
                    case 1:
                        System.out.println("RIGHT");
                        break;
                    case 2:
                        System.out.println("UP");
                        break;
                    case 3:
                        System.out.println("DOWN");
                        break;
                }
            }
            ++i;
        }

    }

}

class TrafficLight implements ITrafficLight
{

    private String id;

    private TLState currentState;

    private TLPosition tlPosition;

    private TLDisplay display;

    // this will provide the mechanism for identifying which lanes are allowed false(default) means its either a
    // redsignal or traffic light isn't powered up.
    private boolean[] allowedLanes;

    public TrafficLight(TLState initialState, TLPosition tlPosition, boolean[] defaultAllowedLanes)
    {
        this.currentState = initialState;
        this.allowedLanes = defaultAllowedLanes;
        this.tlPosition = tlPosition;
        this.display = new TLDisplay(defaultAllowedLanes);
    }

    @Override
    public void changeState()
    {
        currentState = currentState.nextState();
    }

    @Override
    public TLState getCurrentState()
    {
        return this.currentState;
    }

    @Override
    public String getUuid()
    {
        return this.id;
    }

    @Override
    public TLPosition getPosition()
    {
        return this.tlPosition;
    }

    @Override
    public void display()
    {
        this.display.display();
        ;
    }

}

interface ITLStateConfiguration
{
    Map<TLState, Long> getStateConfiguration();

    Queue<TLPosition> getTLOrdering();
}

class TLStateConfiguration implements ITLStateConfiguration
{

    private Map<TLState, Long> durationConfig;

    private Queue<TLPosition> executionOrder;

    public TLStateConfiguration()
    {
        durationConfig = new HashMap<>();
        executionOrder = new LinkedList<>();
        durationConfig.put(TLState.RED, 30L);
        durationConfig.put(TLState.GREEN, 20L);
        durationConfig.put(TLState.ORANGE, 5L);
        durationConfig.put(TLState.YELLOW, 5L);
        executionOrder.add(TLPosition.LEFT);
        executionOrder.add(TLPosition.RIGHT);
        executionOrder.add(TLPosition.FRONT);
        executionOrder.add(TLPosition.BACK);
    }

    @Override
    public Map<TLState, Long> getStateConfiguration()
    {
        return durationConfig;
    }

    @Override
    public Queue<TLPosition> getTLOrdering()
    {

        return executionOrder;
    }

}

class TrafficLightDriver implements TLLight
{
    private Map<ITrafficLight, TLState> trafficLights;

    private TLStateConfiguration tlConfiguration;

    private AtomicBoolean stop;

    public TrafficLightDriver(TLStateConfiguration tlConfiguration)
    {
        this.trafficLights = new HashMap<>();
        this.tlConfiguration = tlConfiguration;
        this.stop = new AtomicBoolean(true);
    }

    @Override
    public void register(ITrafficLight trafficLight)
    {
        this.trafficLights.put(trafficLight, trafficLight.getCurrentState());
    }

    @Override
    public void deregister(ITrafficLight trafficLight)
    {
        this.trafficLights.remove(trafficLight);
    }

    @Override
    public void notifyOthers(ITrafficLight trafficLight)
    {
        trafficLights.entrySet().stream().filter(entry -> !entry.getKey().equals(trafficLight)).map(Entry::getKey)
            .forEach(this::notifyOthers);
    }

    @Override
    public void start()
    {
        this.stop.getAndSet(Boolean.FALSE);
        Map<TLState, Long> stateDuration = this.tlConfiguration.getStateConfiguration();
        Map<TLPosition, ITrafficLight> tlPostionConfig = new HashMap<>();
        trafficLights.forEach((k, v) -> {
            // reset every lights to RED State.
            while (k.getCurrentState().compareTo(TLState.RED) != 0) {
                k.changeState();
            }
            tlPostionConfig.put(k.getPosition(), k);
        });

        Queue<TLPosition> ordering = this.tlConfiguration.getTLOrdering();
        Timer timer = new Timer();
        while (!stop.get()) {
            // 2 traffic lights -> 60s (30,20,5,5)
            // 1-> 60s -> [30,50,55,60]
            // 2-> 60s -> [25,25,5,5]

            TLPosition tlToTrigger = ordering.poll();
            ITrafficLight trafficlLight = tlPostionConfig.get(tlToTrigger);
            TLState state = trafficlLight.getCurrentState();
            long duration = stateDuration.get(state);
            AtomicInteger start1 = new AtomicInteger(0);
            TimerTask task1 = new TimerTask()
            {
                @Override
                public void run()
                {
                    System.out.println("---------Task 1--------@" + start1.get() + "s");
                    trafficlLight.display();
                    if (start1.get() == 30) {
                        trafficlLight.changeState();
                    }
                    start1.getAndIncrement();
                    System.out.println("---------Task 1--------ended");
                }
            };

            timer.scheduleAtFixedRate(task1, 1000, 1000);
            ordering.add(tlToTrigger);
            TLPosition tlToTriggerNext = ordering.poll();
            ITrafficLight trafficlLightNext = tlPostionConfig.get(tlToTriggerNext);
            TLState stateNext = trafficlLightNext.getCurrentState();
            long durationNext = stateDuration.get(stateNext);
            AtomicInteger start2 = new AtomicInteger(25);
            TimerTask task2 = new TimerTask()
            {
                @Override
                public void run()
                {
                    System.out.println("---------Task 2--------@" + start2.get() + "s");
                    trafficlLight.display();
                    if (start2.get() == 30) {
                        trafficlLightNext.changeState();
                        trafficlLightNext.changeState();
                    }
                    start2.getAndIncrement();
                    System.out.println("---------Task 2--------ended");

                }
            };

            timer.scheduleAtFixedRate(task2, 1000, 1000);
            ordering.add(tlToTriggerNext);
        }
    }

    public static void main(String[] args)
    {
        TLLight trafficLight = new TrafficLightDriver(new TLStateConfiguration());
        boolean[] defaultAllowedLanes = {true, true, true, true};
        TrafficLight trafficLightLeft = new TrafficLight(TLState.YELLOW, TLPosition.LEFT, defaultAllowedLanes);
        TrafficLight trafficLightRight = new TrafficLight(TLState.YELLOW, TLPosition.RIGHT, defaultAllowedLanes);
        TrafficLight trafficLightFront = new TrafficLight(TLState.YELLOW, TLPosition.FRONT, defaultAllowedLanes);
        TrafficLight trafficLightBack = new TrafficLight(TLState.YELLOW, TLPosition.BACK, defaultAllowedLanes);
        trafficLight.register(trafficLightLeft);
        trafficLight.register(trafficLightRight);
        trafficLight.register(trafficLightFront);
        trafficLight.register(trafficLightBack);
        trafficLight.start();
    }

}
