# _Step_Semester3

Java solutions for the STEP Semester 3 (CodInClub) assignments, one folder per week.

| Folder | Topic | Programs |
|--------|-------|----------|
| `Week2/`  | Strings: `length()`, `split()`, `StringBuilder`, validation | 5 |
| `Week4/`  | Array algorithms: prefix/suffix products, Kadane, two pointers, prefix-sum map, binary search | 5 |
| `Week5/`  | Arrays and methods: pass-by-reference, 2D arrays, overloading, `Comparable` | 5 |
| `Week6/`  | Classes and objects: constructors, `this(...)`, references, `static` | 5 |
| `Week7/`  | Encapsulation and access control | 5 |
| `Week8/`  | Polymorphism with abstract classes (stdin input) | 5 |
| `Week9/`  | Abstraction: abstract classes vs interfaces (stdin input) | 5 |
| `Week10/` | Array problems 4, 6, 7, 8, 10 with complexity notes | 5 |

## Running a program

Every file is self-contained. From the repository root:

```bash
# Programs with built-in sample runs (Weeks 2, 4, 5, 6, 7, 10)
java Week4/A3_ThreeSum.java

# Programs that read from standard input (Weeks 8 and 9)
printf "3\nSTUDENT 200\nSTAFF 300\nGUEST 150\n" | java Week8/P1_CanteenBillingCounter.java
```

Or compile first:

```bash
javac -d out Week2/P2_WordReversalEncoder.java
java -cp out P2_WordReversalEncoder
```

Compile one file at a time. Some weeks reuse class names (for example `Employee` in
`Week6/M3` and `Week6/M5`), so compiling a whole folder at once would clash.

Requires JDK 17 or newer (the Week 8 and 9 programs use `switch` expressions).

## Notes

- Week 7, Problem 1 names its class `GameCharacter` instead of `Character`, so it does not
  hide `java.lang.Character`.
- Week 5, Problem 5 uses these draft rules: 10+ matches qualifies on experience alone;
  otherwise a player needs 5+ matches and must not be injured.
