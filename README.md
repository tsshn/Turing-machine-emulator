# Turing Machine Emulator
Turing machine emulator.<br>
Step by step displaying.<br>

### Alphabet creation
```java
char[] initialAlphabet = {'0', '1', '*', ' '};
```

### Input specification
```java
char[] initialLine = {'*', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '*'};
```

### State creation
```java
State Q1 = new State();
State Q2 = new State();
...
```

### Instructions creation
```java
Q1.subStateAdd('0', '0', 3, Q0, alphabet.alphabet);
Q1.subStateAdd('1', '1', 3, Q0, alphabet.alphabet);
...
```
<b>Signature:</b> `input`, `output`, `direction`: (`1 - right`, `2 - left`, `3 - stay`), `next state`, `alphabet`.
