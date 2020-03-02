# John Lewis Coding Challenge 21, part 2

<https://coding-challenges.jl-engineering.net/challenges/challenge-21/>

Solving Sudokus again, this time using (more) recursion. This solution improves on the previous one by generating all solutions for a puzzle (I think).

See also <https://github.com/Hives/jl-coding-challenge-21>

## Still to do

1. Tidy up tests. Finish custom `isValidSolutionTo` assertion so it checks the numbers 1-9 are in the right place. Use it in the tests.
2. The checks that a board is invalid/complete are buried in the `deduceUntilExhausted` function. They're quite important for understanding what's happening though. Pull them out into their own function?