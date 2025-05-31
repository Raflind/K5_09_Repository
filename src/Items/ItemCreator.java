package Items;

public interface ItemCreator<T> {
    public T create();
    public String getName();
}
