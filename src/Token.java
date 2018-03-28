// package ca.akinyele.seg2506.devoir2;

class Token {

    private Object value;
    private Token next;

    Token(Object value) {
        this(value, null);
    }

    private Token(Object value, Token next) {
        this.value = value;
        this.next = next;
    }

    public Object getValue() {
        return value;
    }

    public void setNext(Token next) {
        this.next = next;
    }

    private Token getNext() {
        return next;
    }

    private boolean hasNext() {
        return getNext() != null && getNext().isValid();
    }

    public boolean isValid() {
        return getValue() != null && !getValue().toString().trim().isEmpty();
    }

    public boolean is(Object value) {
        if (!isValid()) {
            System.err.println("Warning: Invalid token (no value)");
            return false;
        }
        return value != null && (value == this.value || this.value.equals(value));
    }

    @Override
    public String toString() {
        String v = getValue() == null ? "null" : value.toString();
        if (hasNext()) {
            v = v.concat(" --> ");
            v = v.concat("(").concat(next.getValue().toString()).concat(")");
        }
        return "Token (".concat(v).concat(")");
    }
}
