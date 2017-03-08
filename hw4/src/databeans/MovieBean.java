/**
 * @author Qianying Chen Date:2016/12/13 
 * andrewId:qianyinc
 * course number:08-672
 */
package databeans;
import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class MovieBean {
    
private int id;
private int userId;
private String name;
private String description;
private double ratings;

public int getId() {
    return id;
}
public void setId(int i) {
    this.id = i;
}
public int getUserId() {
    return userId;
}
public void setUserId(int i) {
    this.userId = i;
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}
public double getRatings() {
    return ratings;
}
public void setRatings(double ratings) {
    this.ratings = ratings;
}


}
