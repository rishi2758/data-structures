package arrays;

import java.util.ArrayList;
import java.util.List;

//This has bug
public class RangeModule {

    private List<int[]> intervals;

    public RangeModule() {
        intervals = new ArrayList<>();
    }

    public void addRange(int left, int right) {
        if (intervals.isEmpty()) {
            intervals.add(new int[]{left, right - 1});
        } else {
            intervals = merge(insert(intervals, left, right));
        }
    }

    public void queryRange(int left, int right) {
        int ans = findMinimumRange(left);

        if (ans != -1) {
            int[] interval = intervals.get(ans);
        }
    }

    public void removeRange(int left, int right) {
        int lmin = findMinimumRange(left);
        if(lmin != -1) {
            int[] interval = intervals.remove(lmin);
            if(interval[0] == left) {
                if(right - 1 >= interval[1]) {
                    //nothing to add , already deleted.
                }else {
                    addRange(right, interval[1]+1);
                }
            }else if (left < interval[1]){
                if(right - 1 >= interval[1]) {
                    addRange(interval[0],left);
                }else {
                    addRange(interval[0] , left);
                    addRange(right, interval[1]+1);
                }
            }
        }else {
            int rmin = findMinimumRange(right-1);
            if(rmin != -1) {
                int[] interval = intervals.remove(rmin);
                if(interval[0] <= right-1) {
                    addRange(right,interval[1]+1);
                }else if (right - 1 < interval[1]) {
                    addRange(right,interval[1]+1);
                }
            }
        }
    }

    private int findMinimumRange(int left) {
        int low = 0;
        int high = intervals.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (intervals.get(mid)[0] <= left) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private List<int[]> insert(List<int[]> intervals, int left, int right) {
        int low = 0;
        int high = intervals.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (intervals.get(mid)[0] > left) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        List<int[]> copyOf = new ArrayList<>();

        if (low != -1) {
            copyOf = new ArrayList<>(intervals.subList(0, low));
        }

        copyOf.add(new int[]{left, right - 1});
        copyOf.addAll(intervals.subList(low, intervals.size()));

        return copyOf;
    }

    private List<int[]> merge(List<int[]> intervals) {
        int n = intervals.size();
        List<int[]> ans = new ArrayList<>();
        int f = 0;
        while (f < n) {
            if (ans.isEmpty()) {
                ans.add(intervals.get(f));
            } else {
                int[] prev = ans.get(ans.size() - 1);
                if (intervals.get(f)[0] <= prev[1]) {
                    ans.set(ans.size() - 1, new int[]{prev[0], Math.max(prev[1], intervals.get(f)[1])});
                } else {
                    ans.add(intervals.get(f));
                }
            }
            ++f;
        }
        return ans;
    }

    /*
    * Reference Code:
    * class RangeModule {
            List<int[]> list;

            public RangeModule() {
                list = new ArrayList<>();
            }

            public void addRange(int left, int right) {
                List<int[]> tmp = new ArrayList<>();
                int i = binarySearch(left);
                tmp.addAll(list.subList(0, i));

                while (i < list.size() && !(list.get(i)[0] > right)) {
                    left = Math.min(left, list.get(i)[0]);
                    right = Math.max(right, list.get(i)[1]);
                    i++;
                }
                tmp.add(new int[]{left, right});
                tmp.addAll(list.subList(i, list.size()));
                list = tmp;
            }

            public boolean queryRange(int left, int right) {
                // use binary search for query
                int i = binarySearch(left);
                if (i == list.size()) return false;
                return list.get(i)[0] <= left && list.get(i)[1] >= right;
            }

            private int binarySearch(int k) {
                // find the first element in list such that it's right >= k
                // return list size if no such element exist
                if (list.size() == 0) return list.size();

                int l = 0, r = list.size() - 1;
                while (l + 1 < r) {
                    int m = l + (r - l) / 2;
                    if (list.get(m)[1] >= k) {
                        r = m;
                    } else {
                        l = m;
                    }
                }

                if (list.get(l)[1] >= k) return l;
                if (list.get(r)[1] >= k) return r;
                return list.size();
            }

            public void removeRange(int left, int right) {
                List<int[]> tmp = new ArrayList<>();
                int i = binarySearch(left);
                tmp.addAll(list.subList(0, i));

                while (i < list.size() && !(list.get(i)[0] >= right)) {
                    int[] r = list.get(i);
                    if (left <= r[0]) {
                        if (right < r[1]) {
                            tmp.add(new int[]{right, r[1]});
                        }
                    } else {
                        if (right < r[1]) {
                            tmp.add(new int[]{r[0], left});
                            tmp.add(new int[]{right, r[1]});
                        } else {
                            tmp.add(new int[]{r[0], left});
                        }
                    }
                    i++;
                }

                tmp.addAll(list.subList(i, list.size()));
                list = tmp;
            }
        }
    * */

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        RangeModule rm = new RangeModule();
        /*for (int[] interval :
                intervals) {
            rm.addRange(interval[0],interval[1]);
        }
        [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
        Output
        [null, null, null, true, false, true]
        */
        rm.addRange(10, 20);

        rm.removeRange(15, 22);
        rm.queryRange(10, 14);
        rm.queryRange(13, 15);
        rm.queryRange(16, 17);
    }

}