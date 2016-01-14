package Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Data.CommunicationData;
import Data.Information;
import Data.InviteRecord;
import Data.PayData;

/**
 * Created by Administrator on 2016/1/13.
 */
public class RequestManager {
    private static RequestManager requestManager;
    private LoginListener loginListener;
    private RegisterListener registerListener;
    private Context context;
    private List<Information> list;
    private Information information;
    private List<PayData> payDataList;
    private List<InviteRecord> inviteRecordList;
    private HashMap<String,List<CommunicationData>> hashMap;

    private RequestManager(Context context){
        this.context=context;
    }
    public static RequestManager getRequestManager(Context context){
        if(requestManager!=null){
            return requestManager;
        }else {
            requestManager=new RequestManager(context);
            return requestManager;
        }
    }

    public void sendLoginRequest(String account,String password,LoginListener listener){
        loginListener=listener;
        //执行发送请求操作

        //模拟接受数据
        Boolean isSuccessed=imitateReceive(account,password);
        if(isSuccessed) {
            //解析返回XML文件
            AssetManager assetManager = context.getAssets();
            try {
                InputStream is = assetManager.open("login_success_data.xml");
                list=parseXML_friendprofile(is);
                InputStream is1 = assetManager.open("login_success_data.xml");
                information=parseXML_userinformation(is1);
                InputStream is2 = assetManager.open("login_success_data.xml");
                hashMap=parseXML_communicationdata(is2);
                InputStream is3 = assetManager.open("login_success_data.xml");
                payDataList=parseXML_payData(is3);
                InputStream is4 = assetManager.open("login_success_data.xml");
                inviteRecordList=parseXML_inviterecord(is4);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //回调登录监听函数
            loginListener.loginFinished(true,list,information,hashMap,payDataList,inviteRecordList);
        } else {
            loginListener.loginFinished(false,null,null,null,null,null);
        }
    }

    private List<InviteRecord> parseXML_inviterecord(InputStream is4) {
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(is4, "UTF-8");
            int eventType = parser.getEventType();
            InviteRecord inviteRecord =null;
            List<InviteRecord> inviteList = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                        inviteList = new ArrayList<InviteRecord>();// 实例化集合类
                        break;
                    case XmlPullParser.START_TAG://开始读取某个标签
                        //通过getName判断读到哪个标签，然后通过nextText()获取文本节点值，或通过getAttributeValue(i)获取属性节点值
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("invite")) {
                            inviteRecord = new InviteRecord();
                        } else if (inviteRecord != null) {
                            if (name.equalsIgnoreCase("userid")) {
                                inviteRecord.setUserid(parser.nextText());// 如果后面是Text元素,即返回它的值
                            } else if (name.equalsIgnoreCase("time")) {
                                inviteRecord.setTime(parser.nextText());
                            }
                            else if (name.equalsIgnoreCase("successed")) {
                                inviteRecord.setIfSuccessed(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:// 结束元素事件
                        if (parser.getName().equalsIgnoreCase("invite")&& inviteRecord != null) {
                            inviteList.add(inviteRecord);
                            inviteRecord = null;
                        }
                        break;
                }
                eventType = parser.next();
            }
            return inviteList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<PayData> parseXML_payData(InputStream is3) {
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(is3, "UTF-8");
            int eventType = parser.getEventType();
            PayData payData =null;
            List<PayData> payList = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                        payList = new ArrayList<PayData>();// 实例化集合类
                        break;
                    case XmlPullParser.START_TAG://开始读取某个标签
                        //通过getName判断读到哪个标签，然后通过nextText()获取文本节点值，或通过getAttributeValue(i)获取属性节点值
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("pay")) {
                            payData = new PayData();
                        } else if (payData != null) {
                            if (name.equalsIgnoreCase("count")) {
                                payData.setCount(parser.nextText());// 如果后面是Text元素,即返回它的值
                            } else if (name.equalsIgnoreCase("time")) {
                                payData.setTime(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:// 结束元素事件
                        if (parser.getName().equalsIgnoreCase("pay")&& payData != null) {
                            payList.add(payData);
                            payData = null;
                        }
                        break;
                }
                eventType = parser.next();
            }
            return payList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private HashMap<String,List<CommunicationData>> parseXML_communicationdata(InputStream is2) {
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(is2, "UTF-8");
            int eventType = parser.getEventType();
            String key = null;
            List<CommunicationData> list=null;
            CommunicationData com =null;
            HashMap<String,List<CommunicationData>> hash = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                        hash = new HashMap<String,List<CommunicationData>>();// 实例化集合类
                        break;
                    case XmlPullParser.START_TAG://开始读取某个标签
                        //通过getName判断读到哪个标签，然后通过nextText()获取文本节点值，或通过getAttributeValue(i)获取属性节点值
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("communication")) {
                            list=new ArrayList<CommunicationData>();
                        } else if (name.equalsIgnoreCase("friend_id")) {
                                key=parser.nextText();
                            }else if(name.equalsIgnoreCase("comu")){
                            com=new CommunicationData();
                        }
                        else if(com!=null) {
                            if (name.equalsIgnoreCase("year")) {
                                com.setYear(parser.nextText());
                            } else if (name.equalsIgnoreCase("month")) {
                                com.setMonth(parser.nextText());
                            } else if (name.equalsIgnoreCase("day")) {
                                com.setDay(parser.nextText());
                            } else if (name.equalsIgnoreCase("hour")) {
                                com.setHour(parser.nextText());
                            } else if (name.equalsIgnoreCase("minute")) {
                                com.setMinite(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:// 结束元素事件
                        if(parser.getName().equalsIgnoreCase("comu")&&com!=null){
                            list.add(com);
                            com = null;
                        }
                        if (parser.getName().equalsIgnoreCase("communication")&& list != null) {
                            hash.put(key,list);
                            key=null;
                            list=null;
                        }
                        break;
                }
                eventType = parser.next();
            }
            return hash;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("TAG","return null");
        return null;
    }

    private Information parseXML_userinformation(InputStream inputStream) {
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();;
            Information information1 =null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                        break;
                    case XmlPullParser.START_TAG://开始读取某个标签
                        //通过getName判断读到哪个标签，然后通过nextText()获取文本节点值，或通过getAttributeValue(i)获取属性节点值
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("userinformation")) {
                            information1 = new Information();
                        } else if (information1 != null) {
                            if (name.equalsIgnoreCase("username")) {
                                information1.setUsername(parser.nextText());// 如果后面是Text元素,即返回它的值
                            } else if (name.equalsIgnoreCase("name")) {
                                information1.setName(parser.nextText());
                            }
                            else if (name.equalsIgnoreCase("nickname1")) {
                                information1.setNickname1(parser.nextText());
                            }
                            else if (name.equalsIgnoreCase("nickname2")) {
                                information1.setNickname2(parser.nextText());
                            }
                            else if (name.equalsIgnoreCase("phone")) {
                                information1.setPhone(parser.nextText());
                            }
                            else if (name.equalsIgnoreCase("sex")) {
                                information1.setSex(parser.nextText());
                            }
                            else if (name.equalsIgnoreCase("integration")) {
                                information1.setIntegration(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:// 结束元素事件
                        if (parser.getName().equalsIgnoreCase("userinformation")&& information1 != null) {
                            return information1;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("TAG","return null");
        return null;
    }

    private List<Information> parseXML_friendprofile(InputStream inputStream) {
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();
            Information information =null;
            List<Information> informationList = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                        informationList = new ArrayList<Information>();// 实例化集合类
                        break;
                    case XmlPullParser.START_TAG://开始读取某个标签
                        //通过getName判断读到哪个标签，然后通过nextText()获取文本节点值，或通过getAttributeValue(i)获取属性节点值
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("friend")) {
                            information = new Information();
                        } else if (information != null) {
                            if (name.equalsIgnoreCase("username")) {
                                information.setUsername(parser.nextText());// 如果后面是Text元素,即返回它的值
                            } else if (name.equalsIgnoreCase("name")) {
                                information.setName(parser.nextText());
                            }
                            else if (name.equalsIgnoreCase("nickname1")) {
                                information.setNickname1(parser.nextText());
                            }
                            else if (name.equalsIgnoreCase("nickname2")) {
                                information.setNickname2(parser.nextText());
                            }
                            else if (name.equalsIgnoreCase("phone")) {
                                information.setPhone(parser.nextText());
                            }
                            else if (name.equalsIgnoreCase("sex")) {
                                information.setSex(parser.nextText());
                            }
                            else if (name.equalsIgnoreCase("information")) {
                                information.setIntro(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:// 结束元素事件
                        if (parser.getName().equalsIgnoreCase("friend")&& information != null) {
                            informationList.add(information);
                            information = null;
                        }
                        break;
                }
                eventType = parser.next();
            }
            return informationList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface LoginListener{
        void loginFinished(boolean successed, List<Information> informationList, Information userInformation,HashMap<String,List<CommunicationData>> hashmap,List<PayData> payData,List<InviteRecord> inviteRecord);
    }
    public void setLoginListener(LoginListener listener){
        this.loginListener=listener;
    }

    public void sendRegisterRequest(String account,String password,String inviter,RegisterListener listener){
        //执行注册请求操作

        //解析返回xml文件

        //回调注册监听函数
        registerListener=listener;
        registerListener.registerFinished(true);
    }
    public interface RegisterListener{
        void registerFinished(boolean successed);
    }
    public void setRegisterListener(RegisterListener listener){
        this.registerListener=listener;
    }

    private boolean imitateReceive(String account, String password) {
        if(account.equals("hawx")&&password.equals("1111111")){
            return true;
        }else {
            return false;
        }
    }
}
