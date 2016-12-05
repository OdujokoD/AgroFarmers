package model;

/**
 * Created by Larry on 6/16/2016.
 */
public class Request {

    private int request_id;
    private String product;
    private int weight;
    private String transport_type;
    private String status;

    public Request()
    {

    }

    public Request(int request_id, String product, int weight, String transport_type, String status)
    {
        setRequest_id(request_id);
        setProduct(product);
        setWeight(weight);
        setTransport_type(transport_type);
        setStatus(status);
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getTransport_type() {
        return transport_type;
    }

    public void setTransport_type(String transport_type) {
        this.transport_type = transport_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
