CREATE TABLE `friend` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `active` char(1) COLLATE utf8_unicode_ci NOT NULL,
                        `birthday` datetime NOT NULL,
                        `email_address` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
                        `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `wish_log` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `date` datetime NOT NULL,
                          `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
                          `wished` char(1) COLLATE utf8_unicode_ci NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
