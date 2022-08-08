import React from "react";

import {
    BrowserRouter as Router,
    Route
} from "react-router-dom" 

import Home from "./home";
import User from "./person";

const Webpages = () => {
    return (
        <Router>
            <Route path="/" component={Home} />
            <Route path="/person" component={User} />
        </Router>
    )
}


export default Webpages;