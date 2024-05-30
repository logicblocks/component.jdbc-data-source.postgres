(ns component.jdbc.data-source.postgres.component
  (:require
   [com.stuartsierra.component :as component]
   [component.jdbc.data-source.postgres.data-sources :as data-sources]
   [component.support.logging :as comp-log]))

(defrecord PostgresJdbcDataSource
  [configuration logger datasource]

  component/Lifecycle
  (start [component]
    (comp-log/with-logging logger :component.jdbc.data-source.postgres
      {:phases {:before :starting :after :started}
       :context {:configuration configuration}}
      (assoc component
        :datasource (data-sources/postgres-data-source configuration))))

  (stop [component]
    (comp-log/with-logging logger :component.jdbc.data-source.postgres
      {:phases {:before :stopping :after :stopped}
       :context {:configuration configuration}}
      (assoc component :datasource nil))))
