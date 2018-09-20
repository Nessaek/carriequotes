import React, { Component } from 'react';
import { bindActionCreators } from 'redux';

import { connect } from "react-redux";

import * as thunks from "../actions/index";


function mapStateToProps(state) {
    return {
        quote: state.quote
    }
}

function mapDispatchToProps(dispatch) {
    console.log(dispatch)
    return {
        actions: bindActionCreators(thunks, dispatch)
    };
}



class ConnectedHome extends Component {
    constructor() {
        super();
        this.state = {
            quote: "1"
        }
        this.createQuote = this.createQuote.bind(this);
    }


    componentDidMount() {

    }
    createQuote() {
        const { actions } = this.props
        actions.loadQuote();
    }




    render() {

        return (
            <div>
                <h3>Carrie Quotes!</h3>
                <button onClick={() => this.createQuote()}>Create a Carrie Quote</button>
                <h4>{this.state.quote}</h4>
            </div>
        );
    }
}

const Home = connect(mapStateToProps, mapDispatchToProps)(ConnectedHome);

export default Home