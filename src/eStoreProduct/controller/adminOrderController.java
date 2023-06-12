package eStoreProduct.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eStoreProduct.DAO.OrderDAO;
import eStoreProduct.model.orderModel;

@Controller
public class adminOrderController {

	private OrderDAO od;
	private orderModel om;

	@Autowired
	adminOrderController(OrderDAO ord, orderModel omd) {
		od = ord;
		om = omd;
	}

	@GetMapping("/getOrders")
	@ResponseBody
	public String showEmployeeDocumentsPage() {
		return "orders";
	}

	@RequestMapping(value = "/inserted", method = RequestMethod.POST)
	public String submitOrder(@RequestParam("customerId") Integer customerId,
			@RequestParam("billNumber") Long billNumber, @RequestParam("orderDate") String orderDateStr,
			@RequestParam("total") Double total, @RequestParam("gst") Double gst,
			@RequestParam("paymentReference") Integer paymentReference, @RequestParam("paymentMode") String paymentMode,
			@RequestParam("paymentStatus") String paymentStatus,
			@RequestParam("shippingAddress") String shippingAddress,
			@RequestParam("shippingPincode") Integer shippingPincode,
			@RequestParam("shipmentStatus") String shipmentStatus, @RequestParam("shipmentDate") String shipmentDateStr,
			@RequestParam("processedBy") Integer processedBy, Model model) {
		// Parse the date and timestamp strings
		Timestamp orderDate = Timestamp.valueOf(orderDateStr.replace("T", " ") + ":00");
		Timestamp shipmentDate = Timestamp.valueOf(shipmentDateStr.replace("T", " ") + ":00");

		// Create a new instance of orderModel and set the properties

		om.setOrdr_cust_id(customerId);
		om.setBillNumber(billNumber);
		om.setOrderDate(orderDate);
		om.setTotal(total);
		om.setGst(gst);
		om.setPaymentReference(paymentReference);
		om.setPaymentMode(paymentMode);
		om.setPaymentStatus(paymentStatus);
		om.setShippingAddress(shippingAddress);
		om.setShippingPincode(shippingPincode);
		om.setShipmentStatus(shipmentStatus);
		om.setShipmentDate(shipmentDate);
		om.setOrdr_processedby(processedBy);

		// Save the order details in the database
		od.insertOrder(om);
		model.addAttribute("message", "Candidate details saved successfully!");
		return "inserted";
	}

	@GetMapping("/listOrders")
	@ResponseBody
	public String showOrders(Model model) {
		List<orderModel> orders = od.getAllOrders();
		model.addAttribute("orders", orders);
		return generateOrderListTable(orders);
	}

	public String generateOrderListTable(List<orderModel> orders) {
		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append("<div class=\"container mt-5\">\n").append("<h2>Order List</h2>\n")
				.append("<table class=\"table table-bordered table-hover\">\n").append("<thead class=\"thead-dark\">\n")
				.append("<tr>\n").append("<th>Order ID</th>\n").append("<th>Customer ID</th>\n")
				.append("<th>Bill Number</th>\n").append("<th>Order Date</th>\n").append("<th>Total</th>\n")
				.append("<th>GST</th>\n").append("<th>Payment Reference</th>\n").append("<th>Payment Mode</th>\n")
				.append("<th>Payment Status</th>\n").append("<th>Shipping Address</th>\n")
				.append("<th>Shipping Pincode</th>\n").append("<th>Shipment Status</th>\n")
				.append("<th>Shipment Date</th>\n").append("<th>Processed By</th>\n").append("</tr>\n")
				.append("</thead>\n").append("<tbody>\n");

		for (orderModel order : orders) {
			String processedBy = "" + order.getOrdr_processedby();
			String buttonColor = (processedBy == "NULL") ? "btn-danger" : "btn-success";

			htmlContent.append("<tr>\n").append("<td>").append(order.getId()).append("</td>\n").append("<td>")
					.append(order.getOrdr_cust_id()).append("</td>\n").append("<td>").append(order.getBillNumber())
					.append("</td>\n").append("<td>").append(order.getOrderDate()).append("</td>\n").append("<td>")
					.append(order.getTotal()).append("</td>\n").append("<td>").append(order.getGst()).append("</td>\n")
					.append("<td>").append(order.getPaymentReference()).append("</td>\n").append("<td>")
					.append(order.getPaymentMode()).append("</td>\n").append("<td>").append(order.getPaymentStatus())
					.append("</td>\n").append("<td>").append(order.getShippingAddress()).append("</td>\n")
					.append("<td>").append(order.getShippingPincode()).append("</td>\n").append("<td>")
					.append(order.getShipmentStatus()).append("</td>\n").append("<td>").append(order.getShipmentDate())
					.append("</td>\n").append("<td>");

			System.out.println(processedBy);
			if (order.getOrdr_processedby() == null) {
				htmlContent.append("<button  class=\"btn-btn-danger " + "\">Unprocessed</button>");
			} else {
				htmlContent.append("<button class=\"btn " + buttonColor + "\">").append(order.getOrdr_processedby())
						.append("</button>");
			}

			htmlContent.append("</td>\n").append("</tr>\n");
		}

		htmlContent.append("</tbody>\n").append("</table>\n").append("</div>\n");

		return htmlContent.toString();
	}

}
