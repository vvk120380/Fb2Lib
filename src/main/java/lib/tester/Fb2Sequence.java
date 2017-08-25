package lib.tester;

/**
 * Created by md.sar.pc6 on 25.08.2017.
 */
public class Fb2Sequence {
    String name;
    Integer number;

    public Fb2Sequence(String name, Integer number)
    {
        this.name = name;
        this.number = number;
    }

    public Fb2Sequence(String name)
    {
        this.name = name;
        this.number = null;
    }

    public Fb2Sequence()
    {
        this.name = null;
        this.number = null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        if (number != null)
         return name + " №" + number;
        else
            return name;
    }
}
