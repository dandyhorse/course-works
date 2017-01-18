<#include "../header.ftl">
<main>
    <div id="content">
        <div class="innertube">

            <div class="page-header">
                <h4>Address form</h4>
            </div>

            <form action="/address/${action_type}" method="POST" class="form-group">
                <input type="hidden" name="id" value="${(address.id)!}"/>

                <div class="form-group">
                    <label class="control-lable">Region</label>
                    <input type="text" name="region" value="${(address.region)!""}"/>
                </div>
                <div class="form-group">
                    <label class="control-lable">Street</label>
                    <input type="text" name="street" value="${(address.street)!""}"/>
                </div>
                <div class="form-group">
                    <label class="control-lable">Home Number</label>
                    <input type="number" name="homeNumber" value="${(address.homeNumber)!}"/>
                </div>
                <div class="form-group">
                    <label class="control-lable">Flat Number</label>
                    <input type="number" name="flatNumber" value="${(address.flatNumber)!}"/>
                </div>
                <div class="form-actions">
                    <input type="submit" value="Submit" class="btn btn-success"/>
                    <a role="button" href="/address" class="btn btn-default">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</main>

<#include "../menu.ftl">
<#include "../footer.ftl">