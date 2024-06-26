# HW04 - Language Model

## Description
Calculate the frequency of words appearing AFTER a keyword A[0] in a given article A[1], and return the most commonly occurring word. Note that each word in A[1] is separated by a single space.
### Examples
**Example 1:**
> **Input:** A = ["臺中市","臺中市 中華民國 直轄市 中臺灣 臺中市 中華民國 臺灣"]    
> **Output:** 中華民國

**Example 2:**
> **Input:** A = ["柯文哲","柯文哲 外科 醫師 政治人物 柯文哲 民眾黨 主席 柯文哲 民眾黨"]    
> **Output:** 民眾黨

### File Structure
```
./
├─ LanguageModel.java               # Abstract Class
├─ HW04.java                        # Runner
├─ HW04_4111056036_1.java           # Version 1
├─ HW04_4111056036_2.java           # Version 2
├─ HW04_4111056036_3.java           # Version 3
├─ HW04_4111056036_4.java           # Version 4
├─ HW04_4111056036_5.java           # Version 5
├─ StopWatch.java                   # Class for Calculate Runtime
├─ build.sh                         # Compile Script
└─ README.md
```

## Score
### Best Version
|  Rank   |      Version      |                      Status                      |         Time          |
|---------|-------------------|--------------------------------------------------|-----------------------|
| 4/106   | HW04_4111056036_1 | Correct                                          | 0.0038681544999999997 |
> Note: The rank is compared to the best submissions from all participants. 

### All Versions
|  Rank   |      Version      |                      Status                      |         Time          |
|---------|-------------------|--------------------------------------------------|-----------------------|
| 8/302   | HW04_4111056036_1 | Correct                                          | 0.0038681544999999997 |
| 9/302   | HW04_4111056036_2 | Correct                                          | 0.0040475956          |
| 10/302  | HW04_4111056036_4 | Correct                                          | 0.004262452200000001  |
| 22/302  | HW04_4111056036_3 | Correct                                          | 0.005399348699999999  |
| 289/302 | HW04_4111056036_5 | Error: function nextPredictToken() Wrong Answer. | N/A                   |
> Note: The ranks are compared to all submissions from all participants.
