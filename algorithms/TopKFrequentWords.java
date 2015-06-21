public class TopKFrequentWords {
  public String[] topKFrequent(String[] combo, int k) {
	if (k <= 0 || combo.length == 0) {
      return new String[0];
    }
    Map<String, Integer> map = new HashMap<String, Integer>();
    for (int i = 0; i < combo.length; i++) {
      if (map.containsKey(combo[i])) {
        map.put(combo[i], map.get(combo[i]) + 1);
      } else {
        map.put(combo[i], 1);
      }
    }
    PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<Map.Entry<String, Integer>>(combo.length, new Comparator<Map.Entry<String, Integer>>(){
      @Override
      public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
        if (e1.getValue() == e2.getValue()) {
          return 0;
        }
        return e1.getValue() < e2.getValue() ? 1 : -1;
      }
    });
    
    for (Map.Entry<String, Integer> entry: map.entrySet()) {
      queue.offer(entry);
    }
    String[] res = new String[Math.min(Math.min(k, combo.length), map.size())];
    for (int i = 0; i < k && i < combo.length && i < map.size(); i++) {
      res[i] = queue.poll().getKey();
    }
    return res;
  }
}