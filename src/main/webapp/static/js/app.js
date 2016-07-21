var React = require('react');
var ReactDOM = require('react-dom');

var Application = React.createClass({
    render: function () {
        return <h1>Hello React from Soring</h1>
    }
});
ReactDOM.render(
    <Application />,
    document.body
);