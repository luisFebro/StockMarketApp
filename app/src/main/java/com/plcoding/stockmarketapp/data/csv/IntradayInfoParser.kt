package com.plcoding.stockmarketapp.data.csv

import com.opencsv.CSVReader
import com.plcoding.stockmarketapp.data.mapper.toIntradayInfo
import com.plcoding.stockmarketapp.data.remote.dto.IntradayInfoDto
import com.plcoding.stockmarketapp.domain.model.IntradayInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton
/*
@Inject constructor(): This constructor is annotated with @Inject, indicating that Dagger should use it for dependency injection. This means Dagger will provide an instance of this class wherever it's needed.
 */

/* Why no parameters in constructor?
When Dagger sees a class annotated with @Inject, it attempts to create an instance of that class by using the constructor annotated with @Inject. In this case, Dagger is responsible for providing the dependencies, and therefore, there is no need to explicitly specify constructor parameters.
 */

@Singleton
class IntradayInfoParser @Inject constructor(): CSVParser<IntradayInfo> {

    override suspend fun parse(stream: InputStream): List<IntradayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val timestamp = line.getOrNull(0) ?: return@mapNotNull null
                    val close = line.getOrNull(4) ?: return@mapNotNull null
                    val dto = IntradayInfoDto(timestamp, close.toDouble())
                    dto.toIntradayInfo()
                }
                .filter {
                    // exp: https://youtu.be/uLs2FxFSWU4?t=7546s
                    // this is 4 instead of 1 to get yesterday data because if it is Monday, it will get Sunday data - which is none - since stock market is closed on this date
                    it.date.dayOfMonth == LocalDate.now().minusDays(4).dayOfMonth
                }
                .sortedBy {
                    it.date.hour
                }
                .also {
                    csvReader.close()
                }
        }
    }
}