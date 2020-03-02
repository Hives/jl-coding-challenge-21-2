# John Lewis Coding Challenge 21
## Second crack

<https://coding-challenges.jl-engineering.net/challenges/challenge-21/>

Solving Sudokus again. This time using (more) recursion.

See also <https://github.com/Hives/jl-coding-challenge-21>

## Still to do

1. Tidy up tests. Finish custom `isValidSolutionTo` assertion so it checks the numbers 1-9 are in the right place. Use it in the tests.
2. The checks that a board is invalid/complete are buried in the `deduceUntilExhausted` function. They're quite important for understanding what's happening though. Pull them out into their own function?