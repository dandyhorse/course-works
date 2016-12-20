<#include "../header.ftl">
<main>
    <div id="content">
        <div class="innertube">

        <div class="page-header">
            <h4>Trade form</h4>
        </div>

        <form action="/trade/${action_type}" method="POST" class="form-group" id="tradeForm">
            <input type="hidden" name="id" value="${trade.id}"/>

             <div class="form-group">
                <label class="control-lable">Cost</label>
                <input type="text" name="cost" value="${trade.cost}"/>
            </div>

             <div class="form-group">
                <label class="control-lable">Trade Date</label>
                <input type="text" name="tradeDate" value="${trade.tradeDate?date?iso_utc}"/>
             </div>


            <div class="form-group">
                <label for="flatList" class="control-lable">Flats</label>
                <select name="flat" id="flatList" form="tradeForm" data-live-search="true" class="form-control">
                    <#list flats as flat>
                        <option value="${flat.id}">
                            ${flat.typeHome}, ${flat.commonSpace}, ${flat.residentSpace}
                        </option>
                    </#list>
                </select>
            </div>

            <div class="form-group">
                <label for="sellerList" class="control-lable">Sellers</label>
                <select name="seller" id="sellerList" form="tradeForm" data-live-search="true" class="form-control">
                    <#list sellers as seller>
                        <option value="${seller.id}">
                            ${seller.fio}, ${seller.phoneNumber}
                        </option>
                    </#list>
                </select>
            </div>

            <div class="form-group">
                <label for="customerList" class="control-lable">Customers</label>
                <select name="customer" id="customerList" form="tradeForm" data-live-search="true" class="form-control">
                    <#list customers as customer>
                        <option value="${customer.id}">
                            ${customer.fio}, ${customer.phoneNumber}
                        </option>
                    </#list>
                </select>
            </div>

            <div class="form-actions">
                <input type="submit" value="Submit" class="btn btn-success"/>
                <a role="button" href="/trade" class="btn btn-default">Cancel</a>
            </div>
        </form>

        </div>
    </div>
</main>

<#include "../menu.ftl">
<#include "../footer.ftl">