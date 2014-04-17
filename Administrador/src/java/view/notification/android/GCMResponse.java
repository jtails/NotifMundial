package view.notification.android;

import java.io.Serializable;

import java.util.List;

public class GCMResponse implements Serializable{
    private static final long serialVersionUID = 2193758673530977103L;
    private String multicast_id;
    private String success;
    private String failure;
    private String canonical_ids;
    private List<Results> results;
    private String errorMessage;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setMulticast_id(String multicast_id) {
        this.multicast_id = multicast_id;
    }

    public String getMulticast_id() {
        return multicast_id;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }

    public String getFailure() {
        return failure;
    }

    public void setCanonical_ids(String canonical_ids) {
        this.canonical_ids = canonical_ids;
    }

    public String getCanonical_ids() {
        return canonical_ids;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public String toString(){
        return "multicast_id:" + this.multicast_id+
        ",success:" + this.success+
        ",failure:" + this.failure+
        ",canonical_ids:" + this.canonical_ids+
        ",Results:" + this.results+
        ",errorMessage: "+this.errorMessage;
    }

}
