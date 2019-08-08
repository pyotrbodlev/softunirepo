<%@ page import="java.util.Calendar" %>
<%@ page import="app.domain.entities.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%for (int i = 0; i < ((User) request.getSession().getAttribute("loggedUser")).getTubes().size(); i++) {%>

<div class="col-md-4">
    <div class="card mb-4 box-shadow">
        <img id="img" class="style-scope yt-img-shadow" alt=""
             src="https://i.ytimg.com/vi/jr_iVTccEnA/hqdefault.jpg?sqp=-oaymwEiCKgBEF5IWvKriqkDFQgBFQAAAAAYASUAAMhCPQCAokN4AQ==&amp;rs=AOn4CLDG2ojCz70JDzb0EhrfX9XKI6XRWg"
             >
        <div class="card-body">
            <p class="card-text"><%=((User) request.getSession().getAttribute("loggedUser")).getTubes().get(i).getTitle()%>
            </p>
            <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary"><a
                            href="${pageContext.request.contextPath}/details?id=<%=((User) request.getSession().getAttribute("loggedUser")).getTubes().get(i).getId()%>">View</a>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<%}%>