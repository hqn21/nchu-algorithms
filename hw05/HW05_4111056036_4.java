public class HW05_4111056036_4 extends WordChain {
    class HashMap<K, V> {
        private static final int DEFAULT_CAPACITY = 16;
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;
        private Node<K, V>[] table;
        private int size;
    
        @SuppressWarnings("unchecked")
        public HashMap() {
            this.table = new Node[DEFAULT_CAPACITY];
        }
    
        @SuppressWarnings("unchecked")
        private void grew() {
            if ((size + 1) == (table.length * DEFAULT_LOAD_FACTOR)) {
                Node<K, V>[] oldTable = table;
                table = new Node[table.length << 1];
                size = 0;
                for (int i = 0; i < oldTable.length; ++i) {
                    Node<K, V> node = oldTable[i];
                    while (node != null) {
                        put(node.key, node.value);
                        node = node.next;
                    }
                }
            }
        }
    
        public void put(K key, V value) {
            grew();
    
            int hash = hash(key);
            int index = calcIndex(hash);
            Node<K, V> node = table[index];
            Node<K, V> newNode = new Node<>(key, value, null);
            if (node == null) {
                table[index] = newNode;
            } else {
                boolean keyRepeat = false;
                Node<K, V> last = null;
                while (node != null) {
                    if (node.key.equals(key)) {
                        keyRepeat = true;
                        node.value = value;
                        break;
                    } else {
                        last = node;
                        node = node.next;
                    }
                }
                if (!keyRepeat) {
                    last.next = newNode;
                }
            }
    
            ++size;
        }
    
        public V get(K key) {
            int hash = hash(key);
            int index = calcIndex(hash);
            Node<K, V> node = table[index];
            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                } else {
                    node = node.next;
                }
            }
            return null;
        }
    
        public int size() {
            return size;
        }
    
        private int hash(Object key) {
            if (key == null) {
                return 0;
            }
    
            int h = key.hashCode();
            h = h ^ (h >>> 16);
    
            return h;
        }
    
        private int calcIndex(int hash) {
            return  (table.length - 1) & hash;
        }

        public V getOrDefault(K key, V defaultValue) {
            V value = get(key);
            return (value != null) ? value : defaultValue;
        }
    
        private class Node<E, A> {
            E key;
            A value;
            Node<E, A> next;
    
            public Node(E key, A value, Node<E, A> next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }
    }

    class Data {
        int length;
        int index;
        int indexAmount;
        Data prev;

        public Data(int length, int index, Data prev) {
            this.length = length;
            this.index = index;
            this.indexAmount = 1;
            this.prev = prev;
        }
    }

    class Position {
        int start;
        int end;

        public Position(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    @Override
    public String sequenceProbability(String[] A) {
        HashMap<Integer, Data> record = new HashMap<Integer, Data>();
        HashMap<Integer, Data> record2;
        HashMap<Integer, Data> record3;

        Position[] checkList = new Position[100];
        int[] checkIdList = new int[100];
        int checkAmount = 0;

        Position[] checkList2;
        int[] checkIdList2;
        int checkAmount2;

        int[] checkIdList3;
        int checkAmount3;

        Data dataInfo;
        Data dataInfo2;
        Data dataInfo3;

        int sum = 0;
        int sum1;
        int sum2;

        int targetLength = A[0].length();
        int dataAmount = A[1].length();

        int nowChecking = 0;
        int dataId = 0;
        int prev = 0;
        
        double probability;
        double maxProbability = 0.0;
        Data maxData = new Data(0, 0, null);

        for(int i = 0; i < dataAmount; ++i) {
            while(i < dataAmount && A[0].charAt(nowChecking) != A[1].charAt(i)) {
                ++i;
                if(nowChecking > 0) {
                    nowChecking = 0;
                }
            }

            ++nowChecking;

            if(nowChecking == targetLength) {
                if(++i < dataAmount && A[1].charAt(i) == ' ') {
                    ++sum;
                    prev = ++i;
                    while(i < dataAmount && A[1].charAt(i) != ' ') {
                        dataId += A[1].charAt(i);
                        ++i;
                    }

                    dataInfo = record.get(dataId);
                    if(dataInfo == null) {
                        checkList[checkAmount] = new Position(prev, i);
                        checkIdList[checkAmount] = dataId;
                        ++checkAmount;
                        dataInfo = new Data(i - prev + 1, ++i, null);
                    } else {
                        ++dataInfo.indexAmount;
                    }
                    record.put(dataId, dataInfo);
                    dataId = 0;
                }
                nowChecking = 0;
            }
        }

        for(int i = 0; i < checkAmount; ++i) {
            sum1 = 0;
            sum2 = 0;
            dataInfo = record.get(checkIdList[i]);

            record2 = new HashMap<Integer, Data>();
            checkList2 = new Position[100];
            checkIdList2 = new int[100];
            checkAmount2 = 0;

            nowChecking = checkList[i].start;
            prev = 0;
            dataId = 0;
            for(int j = 0; j < dataAmount; ++j) {
                while(j < dataAmount && A[1].charAt(nowChecking) != A[1].charAt(j)) {
                    ++j;
                    if(nowChecking > checkList[i].start) {
                        nowChecking = checkList[i].start;
                    }
                }
    
                ++nowChecking;
    
                if(nowChecking == checkList[i].end) {
                    if(++j < dataAmount && A[1].charAt(j) == ' ') {
                        ++sum1;
                        prev = ++j;
                        while(j < dataAmount && A[1].charAt(j) != ' ') {
                            dataId += A[1].charAt(j);
                            ++j;
                        }
    
                        dataInfo2 = record2.get(dataId);
                        if(dataInfo2 == null) {
                            checkList2[checkAmount2] = new Position(prev, j);
                            checkIdList2[checkAmount2] = dataId;
                            ++checkAmount2;
                            dataInfo2 = new Data(j - prev + 1, ++j, dataInfo);
                        } else {
                            ++dataInfo2.indexAmount;
                        }
                        record2.put(dataId, dataInfo2);
                        dataId = 0;
                    }
                    nowChecking = checkList[i].start;
                }
            }

            for(int j = 0; j < checkAmount2; ++j) {
                dataInfo2 = record2.get(checkIdList2[j]);
                record3 = new HashMap<Integer, Data>();
                checkIdList3 = new int[100];
                checkAmount3 = 0;

                nowChecking = checkList2[j].start;
                prev = 0;
                dataId = 0;
                for(int k = 0; k < dataAmount; ++k) {
                    while(k < dataAmount && A[1].charAt(nowChecking) != A[1].charAt(k)) {
                        ++k;
                        if(nowChecking > checkList2[j].start) {
                            nowChecking = checkList2[j].start;
                        }
                    }
        
                    ++nowChecking;
        
                    if(nowChecking == checkList2[j].end) {
                        if(++k < dataAmount && A[1].charAt(k) == ' ') {
                            ++sum2;
                            prev = ++k;
                            while(k < dataAmount && A[1].charAt(k) != ' ') {
                                dataId += A[1].charAt(k);
                                ++k;
                            }
        
                            dataInfo3 = record3.get(dataId);
                            if(dataInfo3 == null) {
                                checkIdList3[checkAmount3] = dataId;
                                ++checkAmount3;
                                dataInfo3 = new Data(k - prev + 1, ++k, dataInfo2);
                            } else {
                                ++dataInfo3.indexAmount;
                            }
                            record3.put(dataId, dataInfo3);
                            dataId = 0;
                        }
                        nowChecking = checkList2[j].start;
                    }
                }

                for(int k = 0; k < checkAmount3; ++k) {
                    dataInfo3 = record3.get(checkIdList3[k]);
                    probability = ((double) dataInfo3.indexAmount / sum2) * ((double) dataInfo3.prev.indexAmount / sum1) * ((double) dataInfo3.prev.prev.indexAmount / sum);
                    // System.out.println(probability);
                    if(probability > maxProbability) {
                        maxProbability = probability;
                        maxData = dataInfo3;
                    }
                }
            }
        }

        // System.out.println(maxProbability);

        return A[0] + " " + A[1].substring(maxData.prev.prev.index - maxData.prev.prev.length, maxData.prev.prev.index - 1) + " " + A[1].substring(maxData.prev.index - maxData.prev.length, maxData.prev.index - 1) + " " + A[1].substring(maxData.index - maxData.length, maxData.index - 1);    
    }
}