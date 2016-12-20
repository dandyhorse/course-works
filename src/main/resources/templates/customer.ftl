<#include "header.ftl">
<main>
    <div id="content">
        <div class="innertube">

        <div class="page-header">
            <h4>Customers</h4>
        </div>

        <a href="/customer/add" class="btn btn-success" role="button">Add record</a>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>fio</th>
                <th>phoneNumber</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <#list customers as customer>
            <tr>
                <td>${customer.fio}</td>
                <td>${customer.phoneNumber}</td>
                <td>
                    <div class="btn-group">

                        <a class="btn btn-default" role="button"
                           href="customer/edit/${customer.id}">Edit</a>

                        <a class="btn btn-default" role="button"
                            onclick="deleteAndRefresh(${customer.id}, '/customer')">
                                Delete
                        </a>

                    </div>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
        </div>
    </div>
</main>

<#include "menu.ftl">
<#include "footer.ftl">