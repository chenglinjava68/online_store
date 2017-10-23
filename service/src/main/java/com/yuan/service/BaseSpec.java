package com.yuan.service;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;

/**
 * @author joryun ON 2017/10/22.
 */
public interface BaseSpec {

    default <T> Path<T> getPath(Path<T> root) {
        return root;
    }

    default <T> Specification<T> build(Object value, String... fields) {
        if (value == null) {
            return null;
        }
        return (root, query, cb) -> {
            Path path = getPath(root);
            for (String field : fields) {
                if (field != null) {
                    path = path.get(field);
                }
            }
            return cb.equal(path, value);
        };
    }
}
