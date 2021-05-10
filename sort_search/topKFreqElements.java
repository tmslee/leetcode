class Solution {
  Map<Integer, Integer> freqMap = new HashMap<>();
  
  public void populateFreqMap (int[] nums) {
      for (Integer num : nums){
          int freq = freqMap.getOrDefault(num, 0);
          freqMap.put(num, freq+1);
      }
  }
  
  public void swap (int i, int j, int[] arr) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
      return;
  }
  
  public int partition (int l, int r, int[] arr) {
      int target = arr[r];
      int targetFreq = freqMap.get(target);
      int swapIdx = l;
      for(int i=l ; i<=r ; i++) {
          int currFreq = freqMap.get(arr[i]);
          if(currFreq > targetFreq) {
              swap(swapIdx, i, arr);
              swapIdx++;
          }
      }
      swap(swapIdx, r, arr);
      return swapIdx;
  }
  
  public void kGreatest (int l, int r, int[] arr, int k) {
      if(l == r) return;
      
      int partitionIdx = partition(l, r, arr);
      if (partitionIdx == k) return;
      else if (partitionIdx < k) kGreatest(partitionIdx+1, r, arr, k);
      else kGreatest(l, partitionIdx-1, arr, k);
  }
  
  public int[] topKFrequent(int[] nums, int k) {
      populateFreqMap(nums);
      List<Integer> uniqueElementList = new ArrayList(freqMap.keySet());
      
      int[] uniqueElementArr = new int[uniqueElementList.size()];
      
      for(int i=0 ; i<uniqueElementList.size() ; i++){
          uniqueElementArr[i] = uniqueElementList.get(i);
      }
      
      kGreatest(0, uniqueElementArr.length-1, uniqueElementArr, k);
      
      int [] ans = new int[k];
      for(int i=0 ; i<k ; i++){
          ans[i] = uniqueElementArr[i];
      }
      
      return ans;
  }
}