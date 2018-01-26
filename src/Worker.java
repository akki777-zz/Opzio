public interface Worker<T, U> {

    U apply(T t);
}
