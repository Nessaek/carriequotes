//action creator
import { fetchQuote } from '../api/index'
import * as types from './actionTypes'


// export const getQuote = () =>
//     (
//         { type: "GET_QUOTE", loading: true })


export function loadQuoteSuccess(quote) {
    return { type: types.LOAD_QUOTE_SUCCESS, quote };
}


export function loadQuote() {
    // make async call to api, handle promise, dispatch action when promise is resolved
    return function (dispatch) {
        return fetchQuote().then(quote => {
            dispatch(loadQuoteSuccess(quote));
        }).catch(error => {
            throw (error);
        });
    };
}

