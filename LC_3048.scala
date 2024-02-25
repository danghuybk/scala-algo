object Solution {
    def earliestSecondToMarkIndices(nums: Array[Int], changeIndices: Array[Int]): Int = {
        /*
        Observations:
            1. return -1 if sum(changeIndices) < sum(nums)
            2. return -1 if len(set(changeIndices)) < n
            3. If it's possible:
                In the last appearance of index i in changeIndices, nums[i] must eq 0
        Approach: 
            1. First guest (using sense):
                1.1. Binary search:
                    Given index j in changeIndices, is it possible to check if we can mark? If yes -> problem can be solve with binary search. Seems yes ???
                TC: O(n * log(m))
                
        */
        
        def binarySearch(n: Int, m: Int): Int = {
            def isPossible(n: Int, m: Int, target: Int): Boolean = {
                var required = 0
                var newchangeIndices = changeIndices.take(target + 1)
                
                var lastIdx = collection.mutable.Map[Int, Int]()
                var kvPair = (-1, -1)
                
                for (i <- newchangeIndices.indices) {
                    lastIdx(newchangeIndices(i)) = i
                }
                
                if (lastIdx.size < n) {
                    return false
                }
                var lastIdxSeq = lastIdx.toSeq.sortBy(_._2)
                for (i <- lastIdxSeq.indices) {
                    kvPair = lastIdxSeq(i)
                    required += nums(kvPair._1 - 1)
                    if (kvPair._2 - i < required) {
                        return false
                    }
                }
                return true
            }
            
            var left = 0
            var right = m - 1
            var mid: Int = 0
            
            if (!isPossible(n, m, right)) {
                return -1
            }
            
            while (left < right) {
                mid = (left + right) / 2
                if (isPossible(n, m, mid)) {
                    right = mid
                } else {
                    left = mid + 1
                }
            }
            return left + 1
        }
        
        // Initialize
        var n = nums.size
        var m = changeIndices.size
        
        // Some early exist
        if (changeIndices.toSet.size < n) {
            return -1
        }
        
        // Main processing
        return binarySearch(n, m)
    }
}