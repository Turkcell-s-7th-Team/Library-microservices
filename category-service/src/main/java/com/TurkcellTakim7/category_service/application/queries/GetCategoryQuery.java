package com.TurkcellTakim7.category_service.application.queries;

import com.TurkcellTakim7.category_service.application.core.Query;

public class GetCategoryQuery implements Query {
        private String id;

        public GetCategoryQuery() {
        }

        public GetCategoryQuery(String id) {
                this.id = id;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }
}
