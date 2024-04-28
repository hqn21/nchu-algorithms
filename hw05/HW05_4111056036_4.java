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
        int dataLength = A[1].length();

        int nowChecking = 0;
        int dataId = 0;
        int prev = 0;
        
        float firstTryProbability;
        float probability;
        float maxProbability = 0;
        Data maxData = new Data(0, 0, null);

        int[] hashTable = new int[997];
        int hashId;
        int firstAmount = 0;
        int firstStart = 0;
        int firstEnd = 0;
        int secondAmount = 0;
        int secondStart = 0;
        int secondEnd = 0;
        int thirdAmount = 0;
        int thirdStart = 0;
        int thirdEnd = 0;

        for(int i = 0; i < dataLength; ++i) {
            while(i < dataLength && A[0].charAt(nowChecking) != A[1].charAt(i)) {
                ++i;
                if(nowChecking > 0) {
                    nowChecking = 0;
                }
            }

            ++nowChecking;

            if(nowChecking == targetLength) {
                if(++i < dataLength && A[1].charAt(i) == ' ') {
                    ++sum;
                    prev = ++i;
                    while(i < dataLength && A[1].charAt(i) != ' ') {
                        dataId += A[1].charAt(i);
                        ++i;
                    }

                    hashId = dataId % 997;
                    if(++hashTable[hashId] > firstAmount) {
                        firstAmount = hashTable[hashId];
                        firstStart = prev;
                        firstEnd = i;
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
                    i = prev - 1;
                }
                nowChecking = 0;
            }
        }

        firstTryProbability = (float) firstAmount / sum;
        hashTable = new int[997];
        nowChecking = firstStart;
        int temp = 0;

        for(int i = 0; i < dataLength; ++i) {
            while(i < dataLength && A[1].charAt(nowChecking) != A[1].charAt(i)) {
                ++i;
                if(nowChecking > firstStart) {
                    nowChecking = firstStart;
                }
            }

            ++nowChecking;

            if(nowChecking == firstEnd) {
                if(++i < dataLength && A[1].charAt(i) == ' ') {
                    ++temp;
                    prev = ++i;
                    while(i < dataLength && A[1].charAt(i) != ' ') {
                        dataId += A[1].charAt(i);
                        ++i;
                    }

                    hashId = dataId % 997;
                    if(++hashTable[hashId] > secondAmount) {
                        secondAmount = hashTable[hashId];
                        secondStart = prev;
                        secondEnd = i;
                    }
                    i = prev - 1;
                    dataId = 0;
                }
                nowChecking = firstStart;
            }
        }

        firstTryProbability *= (float) secondAmount / temp;
        hashTable = new int[997];
        nowChecking = secondStart;
        temp = 0;

        for(int i = 0; i < dataLength; ++i) {
            while(i < dataLength && A[1].charAt(nowChecking) != A[1].charAt(i)) {
                ++i;
                if(nowChecking > secondStart) {
                    nowChecking = secondStart;
                }
            }

            ++nowChecking;

            if(nowChecking == secondEnd) {
                if(++i < dataLength && A[1].charAt(i) == ' ') {
                    ++temp;
                    prev = ++i;
                    while(i < dataLength && A[1].charAt(i) != ' ') {
                        dataId += A[1].charAt(i);
                        ++i;
                    }

                    hashId = dataId % 997;
                    if(++hashTable[hashId] > thirdAmount) {
                        thirdAmount = hashTable[hashId];
                        thirdStart = prev;
                        thirdEnd = i;
                    }
                    i = prev - 1;
                    dataId = 0;
                }
                nowChecking = secondStart;
            }
        }

        firstTryProbability *= (float) thirdAmount / temp;

        boolean firstTry = true;

        for(int i = 0; i < checkAmount; ++i) {
            dataInfo = record.get(checkIdList[i]);

            probability = (float) dataInfo.indexAmount / sum;

            if(probability <= firstTryProbability) {
                continue; 
            }

            record2 = new HashMap<Integer, Data>();
            checkList2 = new Position[100];
            checkIdList2 = new int[100];
            checkAmount2 = 0;

            nowChecking = checkList[i].start;
            prev = 0;
            dataId = 0;

            sum1 = 0;
            for(int j = 0; j < dataLength; ++j) {
                while(j < dataLength && A[1].charAt(nowChecking) != A[1].charAt(j)) {
                    ++j;
                    if(nowChecking > checkList[i].start) {
                        nowChecking = checkList[i].start;
                    }
                }
    
                ++nowChecking;
    
                if(nowChecking == checkList[i].end) {
                    if(++j < dataLength && A[1].charAt(j) == ' ') {
                        ++sum1;
                        prev = ++j;
                        while(j < dataLength && A[1].charAt(j) != ' ') {
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
                        j = prev - 1;
                    }
                    nowChecking = checkList[i].start;
                }
            }

            sum2 = 0;
            for(int j = 0; j < checkAmount2; ++j) {
                dataInfo2 = record2.get(checkIdList2[j]);
                probability = ((float) dataInfo.indexAmount / sum) * ((float) dataInfo2.indexAmount / sum1);

                if(probability <= firstTryProbability) {
                    continue;
                }

                record3 = new HashMap<Integer, Data>();
                checkIdList3 = new int[100];
                checkAmount3 = 0;

                nowChecking = checkList2[j].start;
                prev = 0;
                dataId = 0;
                for(int k = 0; k < dataLength; ++k) {
                    while(k < dataLength && A[1].charAt(nowChecking) != A[1].charAt(k)) {
                        ++k;
                        if(nowChecking > checkList2[j].start) {
                            nowChecking = checkList2[j].start;
                        }
                    }
        
                    ++nowChecking;
        
                    if(nowChecking == checkList2[j].end) {
                        if(++k < dataLength && A[1].charAt(k) == ' ') {
                            ++sum2;
                            prev = ++k;
                            while(k < dataLength && A[1].charAt(k) != ' ') {
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
                            k = prev - 1;
                        }
                        nowChecking = checkList2[j].start;
                    }
                }

                for(int k = 0; k < checkAmount3; ++k) {
                    dataInfo3 = record3.get(checkIdList3[k]);
                    probability = ((float) dataInfo3.indexAmount / sum2) * ((float) dataInfo3.prev.indexAmount / sum1) * ((float) dataInfo3.prev.prev.indexAmount / sum);
                    if(probability <= firstTryProbability) {
                        continue;
                    } else {
                        firstTry = false;
                    }

                    if(probability > maxProbability) {
                        maxProbability = probability;
                        maxData = dataInfo3;
                    }
                }
            }
        }

        if(firstTry) {
            return A[0] + " " + A[1].substring(firstStart, firstEnd) + " " + A[1].substring(secondStart, secondEnd) + " " + A[1].substring(thirdStart, thirdEnd);
        } else {
            return A[0] + " " + A[1].substring(maxData.prev.prev.index - maxData.prev.prev.length, maxData.prev.prev.index - 1) + " " + A[1].substring(maxData.prev.index - maxData.prev.length, maxData.prev.index - 1) + " " + A[1].substring(maxData.index - maxData.length, maxData.index - 1);    
        }
    }
}