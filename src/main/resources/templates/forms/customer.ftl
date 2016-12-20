<#include "../header.ftl">
<main>
    <div id="content">
        <div class="innertube">

            <div class="page-header">
                <h4>Customer form</h4>
            </div>

            <form action="/customer/${action_type}" method="POST" class="form-group">
                <input type="hidden" name="id" value="${(customer.id)!}"/>
                <div class="form-group">
                    <label class="control-lable">F.I.O.</label>
                    <input type="text" name="fio" value="${(customer.fio)!""}"/>
                </div>
                <div class="form-group">
                    <label class="control-lable">Phone Number</label>
                    <input type="text" name="phoneNumber" value="${(customer.phoneNumber)!""}"/>
                </div>
                <div class="form-group">
                    <label class="control-lable">Job</label>
                    <input type="text" name="job" value="${(customer.job)!""}"/>
                </div>
                <div class="form-group">
                    <label class="control-lable">Post</label>
                    <input type="text" name="post" value="${(customer.post)!""}"/>
                </div>
                <div class="form-actions">
                    <input type="submit" value="Submit" class="btn btn-success"/>
                    <a role="button" href="/customer" class="btn btn-default">Cancel</a>
                </div>
            </form>

        </div>
    </div>
</main>

<#include "../menu.ftl">
<#include "../footer.ftl">