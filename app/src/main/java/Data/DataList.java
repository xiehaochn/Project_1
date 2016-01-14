package Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/1/13.
 */
public class DataList implements Serializable {
    private Information userInformation;
    private List<Information> list;
    private List<CommunicationData> communicationDataList;
    private HashMap<String,List<CommunicationData>> hashMap;
    private List<PayData> payData;
    private List<InviteRecord> inviteRecord;
    public DataList(){}
    public DataList(List<Information> list, Information userInformation,HashMap<String,List<CommunicationData>> hashmap,List<PayData> payData,List<InviteRecord> inviteRecord){
        this.list=list;
        this.userInformation=userInformation;
        this.hashMap=hashmap;
        this.payData=payData;
        this.inviteRecord=inviteRecord;
    }
    public DataList(HashMap<String,List<CommunicationData>> hashmap){
        this.hashMap=hashmap;
    }
    public DataList(List<CommunicationData> communicationDataList){
        this.communicationDataList=communicationDataList;
    }
    public List<Information> getList(){
        return list;
    }
    public Information getUserInformation(){
        return userInformation;
    }

    public HashMap<String,List<CommunicationData>> getHashMap() {
        return hashMap;
    }

    public List<CommunicationData> getCommunicationDataList() {
        return communicationDataList;
    }

    public List<PayData> getPayData() {
        return payData;
    }

    public List<InviteRecord> getInviteRecord() {
        return inviteRecord;
    }

    public void setPayData(List<PayData> payData) {
        this.payData = payData;
    }

    public void setInviteRecord(List<InviteRecord> inviteRecord) {
        this.inviteRecord = inviteRecord;
    }
}
