class Solution:
    def earliestSecondToMarkIndices(self, nums: List[int], changeIndices: List[int]) -> int:
        
        def isPossible(target):
            lastIdx = {}
            required = 0
            slicedChangeIndices = changeIndices[:target+1]
            for i, num in enumerate(slicedChangeIndices):
                lastIdx[num] = i
            if len(lastIdx) < n:
                return False
            sortedLastIdx = sorted([[k, v] for (k, v) in lastIdx.items()], key=lambda x:x[1])
            for i, (key, value) in enumerate(sortedLastIdx):
                required += nums[key - 1]
                if value - i < required:
                    return False
            return True
        
        n = len(nums)
        m = len(changeIndices)
        
        left = 0
        right = m - 1
        
        if not isPossible(right):
            return -1
        
        while left < right:
            mid = (left + right) // 2
            if isPossible(mid):
                right = mid
            else:
                left = mid + 1
        return left + 1