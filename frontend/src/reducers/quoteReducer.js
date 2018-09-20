import * as types from './../actions/actionTypes'

export function quoteReducer(state = {}, action) {
    switch (action.type) {

        case types.LOAD_QUOTE_SUCCESS:
            return [...state, action.quote];
        default:
            return state;
    }
};
