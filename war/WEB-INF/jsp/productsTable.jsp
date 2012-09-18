<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<c:set var="table_id">${model.tableName}</c:set>

<script>
  var table_id = "${table_id}";
  var productsTable = $("#" + table_id).dataTable({
    "bJQueryUI" : true
  });

  $("#" + table_id + " tbody").dblclick(
      function(event) {

        // remove row_selected class everywhere
        $(productsTable.fnSettings().aoData).each(function() {
          $(this.nTr).removeClass('row_selected');
        });

        // add row_selected class to the current row
        $(event.target.parentNode).addClass('row_selected');

        var elements = $(event.target.parentNode).children();
        if (elements[0].getAttribute("class") === "dataTables_empty") {
          return;
        }

        var productId = elements[0].innerHTML;

        generateEditForm("editProductFormGenerator.html", {
          productNumber : productId
          isDialog : "yes"
        }, updateExistingTestResult, "Edit Product: "
            + elements[1].innerHTML + " " + elements[2].innerHTML,
            'ProductsTable', decorateEditProductDialog, 550, 500);
      });
</script>

<br />
<jsp:include page="addProduct.jsp" flush="true" />
<br />
<br />

<table id="${table_id}" class="dataTable collectionsTable">
	<thead>
		<tr>
			<th>${model.productNoDisplayName}</th>
			<th>${model.collectionNoDisplayName}</th>
			<c:if test="${model.showproductType==true}">
				<th>${model.productTypeDisplayName}</th>
			</c:if>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="product" items="${model.allProducts}">
			<tr>
				<td>${product.productNumber}</td>
				<td>${product.collectionNumber}</td>
				<c:if test="${product.showproductType}">
					<td>${product.productType}</td>
				</c:if>
			</tr>
		</c:forEach>
	</tbody>
</table>
