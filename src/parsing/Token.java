package parsing;// package ca.akinyele.seg2506.devoir2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Token {

    private static final String TO_IGNORE = "(\\s+|(#.*))";
    private static final String TOKEN_REGEX = "(([0-9]+(\\.[0-9]+)?)|[+\\-*/]|((\\w+)|[()\\[\\]]|,|(\\.{3})|=(=)?|[?:;])|[^#])";

    public enum Type {
        END_EXPRESSION {
            @Override
            public String regex() {
                return ";";
            }
        },
        OPERATION {
            @Override
            public String regex() {
                return "[+\\-*/]";
            }
        },
        FLOAT_NUMBER {
            @Override
            public String regex() {
                return "([0-9]+(\\.[0-9]+)?)f";
            }
        },
        INT_NUMBER {
            @Override
            public String regex() {
                return "[0-9]+";
            }
        },
        DOUBLE_NUMBER {
            @Override
            public String regex() {
                return "([0-9]+(\\.[0-9]+)?)";
            }
        },
        /*LONG_NUMBER {
            @Override
            public String regex() {
                return "[0-9]+";
            }
        },*/
        RETURN {
            public String regex() {
                return "return";
            }
        },
        VARIABLE_TYPE {
            public String regex() {
                return "(var|int|float|double|def|boolean)";
            }
        },
        CONDITION {
            public String regex() {
                return "(if|unless|else|for|while|until)";
            }
        },
        NULL {
            public String regex() {
                return "null";
            }
        },
        CONDITION_SIGN {
            public String regex() {
                return "(==|<|>|<=|=>)";
            }
        },
        EQUAL_SIGN {
            @Override
            public String regex() {
                return "=";
            }
        },
        UNARY_OPERATOR {
            @Override
            public String regex() {
                return "[\\?\\:]";
            }
        },
        METHOD_VARIABLE_SEPARATOR {
            public String regex() {
                return "(,|\\.{3})";
            }
        },
        BRACKET_OPEN {
            public String regex() {
                return "[\\[\\(\\{]";
            }
        },
        BRACKET_CLOSE {
            public String regex() {
                return "[\\]\\)\\}]";
            }
        },
        VAR_METHOD_NAME {
            public String regex() {
                return "[a-zA-Z0-9_]+";
            }
        },
        UNKNOWN {
            public String regex() {
                return null;
            }
        };

        public abstract String regex();
        public boolean isValid(Token token) {
            return token != null && regex() != null && token.getValue() != null
                    && token.getValue().matches(regex());
        }
    }

    private String value;
    private Token next;
    private Type tokenType;

    Token(String value) {
        this(value, null);
    }

    private Token(String value, Token next) {
        this.value = value;
        this.next = next;

        for (Type tokenType : Type.values()) {
            if (tokenType.isValid(this)) {
                this.tokenType = tokenType;
                break;
            }
        }
        if (this.tokenType == null) {
            this.tokenType = Type.UNKNOWN;
        }
    }

    public String getValue() {
        return value;
    }

    public void setNext(Token next) {
        this.next = next;
    }

    private Token getNext() {
        return next;
    }

    public Type getTokenType() {
        return tokenType;
    }

    private boolean hasNext() {
        return getNext() != null && getNext().isValid();
    }

    public boolean isValid() {
        return getValue() != null && !getValue().trim().isEmpty() && !isUnknown();
    }

    public boolean isUnknown() {
        return tokenType == Type.UNKNOWN;
    }

    public boolean is(String value) {
        if (!isValid()) {
            System.err.println("Warning: Invalid token (no value)");
            return false;
        }
        return value != null && (this.value.equals(value));
    }

    public static List<Token> tokensFromLines(List<String> lines) {
        List<Token> tokens = new ArrayList<>();
        if (lines != null && !lines.isEmpty()) {
            for (String line : lines) {
                tokens.addAll(tokensFromString(line));
            }
        }
        return tokens;
    }

    public static List<Token> tokensFromString(String line) {
        List<Token> tokens = new ArrayList<>();
        if (line != null && !line.isEmpty()) {
            Matcher matcher = Pattern.compile(TOKEN_REGEX).matcher(line);
            while (matcher.find()) {
                String match = matcher.group();
                if (!match.matches(TO_IGNORE)) {
                    tokens.add(new Token(match));
                }
            }
        }
        return tokens;
    }

    @Override
    public String toString() {
        String v = getValue() == null ? "null" : value.toString();
        if (hasNext()) {
            v = v.concat(" --> ");
            v = v.concat("(").concat(next.getValue().toString()).concat(")");
        }
        return "Token (".concat(v).concat(" - " + tokenType + " )");
    }
}
