package Data;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/1/13.
 */
public class Information implements Serializable{
     private String name;
     private String nickname1;
     private String nickname2;
     private String phone;
     private String sex;
     private String intro;
     private String username;
     private Bitmap avatar;
     private String integration;

     public Information(String name, String nickname1, String nickname2, String phone, String sex, String intro, String username, Bitmap avatar){
          this.name=name;
          this.nickname1=nickname1;
          this.nickname2=nickname2;
          this.phone=phone;
          this.sex=sex;
          this.intro=intro;
          this.username=username;
          this.avatar=avatar;
     }
     public Information(){
     }
     public String getName(){
          return name;
     }
     public String getNickname1(){
          return nickname1;
     }
     public String getNickname2(){
          return nickname2;
     }
     public String getPhone(){
          return phone;
     }
     public String getSex(){
          return sex;
     }
     public String getIntro(){
          return intro;
     }
     public String getUsername(){
          return username;
     }
     public Bitmap getAvatar(){
          return avatar;
     }
     public String getIntegration(){return integration;}
     public void setName(String name){
          this.name=name;
     }
     public void setNickname1(String nickname1){
          this.nickname1=nickname1;
     }
     public void setNickname2(String nickname2){
          this.nickname2=nickname2;
     }
     public void setPhone(String phone){
          this.phone=phone;
     }
     public void setSex(String sex){
          this.sex=sex;
     }
     public void setIntro(String intro){
          this.intro=intro;
     }
     public void setUsername(String username){
          this.username=username;
     }
     public void setAvatar(Bitmap avatar){
          this.avatar=avatar;
     }
     public void setIntegration(String integration){this.integration=integration;}

}
