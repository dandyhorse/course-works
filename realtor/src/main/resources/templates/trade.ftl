<#include "header.ftl">
<main>
    <div id="content">
        <div class="innertube">

        <div class="page-header">
            <h4>Trades</h4>
        </div>

        <a href="/trade/add" class="btn btn-success" role="button">Add record</a>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>cost</th>
                <th>tradeDate</th>
                <th>flat.id</th>
                <th>flat.typeHome</th>
                <th>customer</th>
                <th>seller</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <#list trades as trade>
            <tr>
                <td>${trade.cost}</td>
                <td>${trade.tradeDate}</td>
                <td>${trade.flat.id}</td>
                <td>${trade.flat.typeHome}</td>
                <td>${trade.customer.fio}</td>
                <td>${trade.seller.fio}</td>
                <td>
                    <div class="btn-group">

                        <a class="btn btn-default" role="button"
                           href="trade/edit/${trade.id}">Edit</a>

                        <a class="btn btn-default" role="button"
                            onclick="deleteAndRefresh(${trade.id}, '/trade')">
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