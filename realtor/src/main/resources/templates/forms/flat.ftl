<#include "../header.ftl">
<main>
    <div id="content">
        <div class="innertube">

            <div class="page-header">
                <h4>Flat form</h4>
            </div>

            <form action="/flat/${action_type}" method="POST" class="form-group" id="flatForm">
                <input type="hidden" name="id" value="${(flat.id)!}"/>

                <div class="form-group">
                    <label for="addressList" class="control-lable">Addresses</label>
                    <select name="address" id="addressList" form="flatForm" data-live-search="true"
                            class="form-control">
                    <#list addresses as address>
                        <option value="${address.id}">
                        ${address.region}, ${address.street}, ${address.homeNumber}
                        </option>
                    </#list>
                    </select>
                </div>

                <div class="form-group">
                    <label class="control-lable">Home Type</label>
                    <input type="text" name="typeHome" value="${(flat.typeHome)!}"/>
                </div>
                <div class="form-group">
                    <label class="control-lable">Common Space</label>
                    <input type="text" name="commonSpace" value="${(flat.commonSpace)!}"/>
                </div>
                <div class="form-group">
                    <label class="control-lable">Resident Space</label>
                    <input type="text" name="residentSpace" value="${(flat.residentSpace)!}"/>
                </div>
                <div class="form-actions">
                    <input type="submit" value="Submit" class="btn btn-success"/>
                    <a role="button" href="/flat" class="btn btn-default">Cancel</a>
                </div>
            </form>

        </div>
    </div>
</main>

<#include "../menu.ftl">
<#include "../footer.ftl">