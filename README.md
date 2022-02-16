# quadratic-java

Project intended to solve quadratic equations 
in the future, but currently only has a mathematical
parser. (it is a cool parser though)

## Use
Fairly self-explanatory, you run it and input an expression :)

```bash
Enter your expression:
2*3 + 1

Input: 2*3 + 1
Tokenized: [`2`, `*`, `3`, `+`, `1`]
Parsed: { name: "PLUS", lhs: { name: "MUL", lhs: { value: 2.0 }, rhs: { value: 3.0 } }, rhs: { value: 1.0 } }

RESULT: 7.0
```

## Notes/References

- Some ideas stolen from here: https://www.youtube.com/watch?v=Yjgh7bfh2FU
- Other ideas stolen from here: 
  - https://en.wikipedia.org/wiki/Recursive_descent_parser
  - https://en.wikipedia.org/wiki/Operator-precedence_parser#Precedence_climbing_method
- Some code probably stolen from here: https://stackoverflow.com/