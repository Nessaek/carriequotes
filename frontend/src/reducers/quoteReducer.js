import * as types from './../actions/actionTypes'

export function quoteReducer(state = false, action) {
    switch (action.type) {
        case types.LOAD_QUOTE_SUCCESS:
            return [...state, action.quote];
        default:
            return state;
    }
};
