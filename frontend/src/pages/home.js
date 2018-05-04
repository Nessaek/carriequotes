import React, { Component } from 'react';
import { bindActionCreators } from 'redux';

import { connect } from "react-redux";

import { loadQuote } from "../actions/index";


function mapStateToProps(state) {
    return {
        quote: state.quote
    }
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(loadQuote, dispatch)
    };
}



class ConnectedHome extends Component {
    constructor() {
        super();
        this.state = {
            output: "test1"
        }
        this.createQuote = this.createQuote.bind(this);
    }


    componentDidMount() {
        console.log(this.props)
        // this.props.fetchQuote()
    }
    createQuote() {

    }




    render() {
        return (
            <div>
                <h3>Carrie Quotes!</h3>
                <button onClick={() => this.createQuote()}>Create a Carrie Quote</button>
                <h4>{this.state.output}</h4>
            </div>
        );
    }
}

const Home = connect(mapStateToProps, mapDispatchToProps)(ConnectedHome);

export default Home