/*
 * Copyright (c) 2024
 * Written by JongSang Han<dogfootmaster@gmail.com>
 * Last modified on 2024/6/9
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
INSERT INTO audience_condition (id, ad_group_id, user_column, operator, column_value) VALUES (1, 1, 'AGE', 'GOE', '30');
INSERT INTO audience_condition (id, ad_group_id, user_column, operator, column_value) VALUES (2, 1, 'AGE', 'LOE', '50');
INSERT INTO audience_condition (id, ad_group_id, user_column, operator, column_value) VALUES (3, 2, 'MONTHLY_SALES', 'GOE', '30000000');
INSERT INTO audience_condition (id, ad_group_id, user_column, operator, column_value) VALUES (4, 3, 'KOREA_REGION', 'EQ', 'SEOUL');
INSERT INTO audience_condition (id, ad_group_id, user_column, operator, column_value) VALUES (5, 4, 'GENDER', 'EQ', 'MAN');
INSERT INTO audience_condition (id, ad_group_id, user_column, operator, column_value) VALUES (6, 5, 'CLASSIFICATION', 'EQ', 'CAFE');
INSERT INTO audience_condition (id, ad_group_id, user_column, operator, column_value) VALUES (7, 6, 'MONTHLY_SALES', 'GOE', '10000000');

