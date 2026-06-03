import java.util.*;

class Solution {
    static class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int idx, int val) {
            update(1, 0, n - 1, idx, val);
        }

        private void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                tree[node] = val;
                return;
            }

            int mid = (l + r) / 2;
            if (idx <= mid) {
                update(node * 2, l, mid, idx, val);
            } else {
                update(node * 2 + 1, mid + 1, r, idx, val);
            }

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        int query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }

        private int query(int node, int l, int r, int ql, int qr) {
            if (ql > r || qr < l) return 0;
            if (ql <= l && r <= qr) return tree[node];

            int mid = (l + r) / 2;
            return Math.max(
                query(node * 2, l, mid, ql, qr),
                query(node * 2 + 1, mid + 1, r, ql, qr)
            );
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        int maxX = 0;
        for (int[] q : queries) {
            maxX = Math.max(maxX, q[1]);
        }

        SegmentTree seg = new SegmentTree(maxX + 1);
        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {
            if (q[0] == 1) {
                int x = q[1];

                Integer prev = obstacles.floor(x);
                Integer next = obstacles.ceiling(x);

                seg.update(x, x - prev);

                if (next != null) {
                    seg.update(next, next - x);
                }

                obstacles.add(x);
            } else {
                int x = q[1];
                int sz = q[2];

                Integer prev = obstacles.floor(x);

                int maxGapBeforeX = seg.query(0, x);
                int tailGap = x - prev;

                int best = Math.max(maxGapBeforeX, tailGap);

                ans.add(best >= sz);
            }
        }

        return ans;
    }
}