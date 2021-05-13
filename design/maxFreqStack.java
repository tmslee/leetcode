class FreqStack {
  Map<Integer, Integer> freqMap;
  Map<Integer, Stack<Integer>> groupMap; //stacks grouped by frequency
  int maxFreq;
  
  public FreqStack() {
      freqMap = new HashMap();
      groupMap = new HashMap();
      maxFreq = 0;
  }
  
  public void push(int val) {
      int freq = freqMap.getOrDefault(val, 0) + 1;
      freqMap.put(val, freq);
      maxFreq = Math.max(freq, maxFreq);
      groupMap.computeIfAbsent(freq, k -> new Stack()).push(val);
  }
  
  public int pop() {
      int val = groupMap.get(maxFreq).pop();
      freqMap.put(val, freqMap.get(val) - 1);
      if(groupMap.get(maxFreq).size() == 0) maxFreq--;
      return val;
  }
}

/**
* Your FreqStack object will be instantiated and called as such:
* FreqStack obj = new FreqStack();
* obj.push(val);
* int param_2 = obj.pop();
*/