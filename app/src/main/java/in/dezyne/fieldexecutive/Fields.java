package in.dezyne.fieldexecutive;


public class Fields {

    int _id;
    String _imagepath;
    String _name;
    String _sex;
    String _age;
    String _address;
    String _salary;
    String _saving;

    public Fields()
    {
    }
public Fields(int id,String imagepath,String name,String sex,String age,String address,String salary,String saving)
{
    this._id = id;
    this._imagepath= imagepath;
    this._name = name;
    this._sex = sex;
    this._age = age;
    this._address = address;
    this._salary = salary;
    this._saving = saving;
}

    public int getID()
    {
        return this._id;
    }
    public void setID(int id)
    {
        this._id= id ;
    }


    public String getImagepath()
    {
        return this._imagepath;
    }
    public void setImagepath(String imagepath)
    {
        this._imagepath=imagepath ;
    }

    public String getName()
    {
        return this._name;
    }
    public void setName(String name)
    {
        this._name=name ;
    }

    public String getSex()
    {
        return this._sex;
    }
    public void setSex(String sex)
    {
        this._sex=sex ;
    }

    public String getAge()
    {
        return this._age;
    }
    public void setAge(String age)
    {
        this._age=age ;
    }

    public String getAddress()
    {
        return this._address;
    }
    public void setAddress(String address)
    {
        this._address=address ;
    }

    public String getSalary()
    {
        return this._salary;
    }
    public void setSalary(String salary)
    {
        this._salary=salary ;
    }

    public String getSaving()
    {
        return this._saving;
    }
    public void setSaving(String saving)
    {
        this._saving=saving ;
    }

}
