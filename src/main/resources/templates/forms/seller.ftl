<#include "../header.ftl">
<main>
    <div id="content">
        <div class="innertube">

        <div class="page-header">
            <h4>Seller form</h4>
        </div>

        <form action="/seller/${action_type}" method="POST" class="form-group" >
            <input type="hidden" name="id" value="${seller.id}" />
            <div class="form-group">
                <label class="control-lable">F.I.O.</label>
                <input type="text" name="fio" value="${seller.fio}" />
            </div>
            <div class="form-group">
                <label class="control-lable">Phone Number</label>
                <input type="text" name="phoneNumber" value="${seller.phoneNumber}" />
            </div>
            <div class="form-actions">
                <input type="submit" value="Submit" class="btn btn-success"/>
                <a role="button" href="/seller" class="btn btn-default">Cancel</a>
            </div>
        </form>

        </div>
    </div>
</main>

<#include "../menu.ftl">
<#include "../footer.ftl">