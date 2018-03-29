# drummerAKI interpreted language

#### Status : NOT functional

## About
This my programming language Java interpreter.

This is an experimental project allowing me to understand a bit better how programming interpreters work (such as 
Python or Ruby).

## So... how weird is it going to be?

My programing language is going to inspired on the following languages:
- Python
- Ruby
- Java
- C++
- JavaScript
- and myself 

## And what does it look like?

Here are snippets of code of what I am trying to achieve.

This method returns a `double` number and takes in at least one parameter.
```
double infinite_params(one, params...) {
    return 1 if (one == null);
    var first = params[0];
    
    return first == 6 ? 0.68 : params[1];
}
```

These are two examples of methods that are empty. A comment that explains the method.
```
# empty methods
void empty() {}
def nothing();
```

An example of a recursive method

```
int factorial(a) {
    if (a <= 1) {
        return 1;
    }
    return a * factorial(a-1);
}
```

## Anything else?

I really want to get this programming language working, especially for myself since there
I want to create services that are written in this language. I am planning on spending at least a year on
developing this language, or at least until the point where I figure out to develop this language in this
language... 
