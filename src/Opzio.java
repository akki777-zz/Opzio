import java.util.Objects;

public class Opzio<T> {

    private final T value;

    private static final Opzio<?> EMPTY = new Opzio<>();

    private Opzio() {
        this.value = null;
    }

    private Opzio(T value) {
        this.value = Objects.requireNonNull(value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Opzio<T> empty() {
        return (Opzio<T>) EMPTY;
    }

    public boolean isPresent() {
        return value != null;
    }

    public Opzio<T> ifPresent(Accepter<? super T> accepter) {
        if (value != null) {
            accepter.accept(value);
        }
        return this;
    }

    public void ifAbsent(Idler emptier) {
        if (value == null) {
            emptier.doNothing();
        }
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

    public T orElseGet(Provider<? extends T> other) {
        return value != null ? value : other.get();
    }

    public static <T> Opzio<T> of(T value) {
        return new Opzio<>(value);
    }

    public static <T> Opzio<T> ofNullable(T value) {
        if (value != null) {
            return of(value);
        } else {
            return empty();
        }
    }

    public static <T> Opzio<T> resolve(Provider<? extends T> resolver) {
        try {
            T result = resolver.get();
            return Opzio.ofNullable(result);
        } catch (NullPointerException e) {
            return empty();
        }
    }


    public <U> Opzio<U> map(Worker<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            return Opzio.ofNullable(mapper.apply(value));
        }
    }

    public Opzio<T> filter(Asserter<? super T> asserter) {
        Objects.requireNonNull(asserter);
        if (!isPresent()) {
            return this;
        } else {
            return asserter.test(value) ? this : empty();
        }

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Opzio)) {
            return false;
        }

        Opzio<?> compareObj = (Opzio<?>) obj;
        return Objects.equals(value, compareObj.value);
    }

    @Override
    public String toString() {
        return value != null ? String.format("Opzio[%s]", value) : "Opzio.empty";
    }
}
