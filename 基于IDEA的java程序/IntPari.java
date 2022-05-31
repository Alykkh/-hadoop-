import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntPari implements WritableComparable {
    private Text first;
    private IntWritable second;
    public void set(Text first, IntWritable second){
        this.first=first;
        this.second=second;


    }
    public IntPari(){
        set(new Text(), new IntWritable());
    }
    public  IntPari(String  first, int second){
        set (new Text(first), new IntWritable(second));
    }
    public IntPari(Text first, IntWritable second){
        set(first, second);
    }
    public Text getFirst(){
        return first;
    }

    public void setFirst(Text first) {

        this.first=first;
    }
    public  IntWritable getSecond(){
        return second;
    }
    public void setSecond(IntWritable second){
        this.second=second;
    }




    @Override
    public void write(DataOutput dataOutput) throws IOException {
        first.write(dataOutput);
        second.write(dataOutput);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        first.readFields(dataInput);
        second.readFields(dataInput);

    }
    public int hashCode(){
        return first.hashCode()*163+second.hashCode();

    }
    public boolean equals(Object O) {
        if (O instanceof IntPari) {
            IntPari tp = (IntPari) O;
            return first.equals(tp.first) && second.equals(tp.second);

        }
        return false;
    }
    public String toString(){
        return first+"\t"+second;
    }
    public  int compareTo(Object O){
        IntPari tp=(IntPari) O;
        int cmp=first.compareTo(tp.first);
         if(cmp!=0){

             return cmp;
         }
         return second.compareTo(tp.second);
    }

}
