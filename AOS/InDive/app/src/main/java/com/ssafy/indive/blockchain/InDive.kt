package com.ssafy.indive.blockchain

import org.web3j.protocol.Web3j
import com.ssafy.indive.blockchain.InDive
import org.web3j.tx.gas.ContractGasProvider
import org.web3j.tx.TransactionManager
import org.web3j.protocol.core.RemoteFunctionCall
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.abi.datatypes.generated.Uint256
import com.ssafy.indive.blockchain.InDive.DonationEventEventResponse
import org.web3j.tx.Contract.EventValuesWithLog
import io.reactivex.Flowable
import io.reactivex.functions.Function
import org.web3j.protocol.core.DefaultBlockParameter
import org.web3j.abi.EventEncoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.*
import org.web3j.crypto.Credentials
import org.web3j.protocol.core.methods.request.EthFilter
import org.web3j.tuples.generated.Tuple5
import kotlin.Throws
import org.web3j.tuples.generated.Tuple2
import org.web3j.protocol.core.methods.response.BaseEventResponse
import org.web3j.protocol.core.methods.response.Log
import org.web3j.tx.Contract
import java.math.BigInteger
import java.util.*

/**
 *
 * Auto generated code.
 *
 * **Do not modify!**
 *
 * Please use the [web3j command line tools](https://docs.web3j.io/command_line.html),
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * [codegen module](https://github.com/web3j/web3j/tree/master/codegen) to update.
 *
 *
 * Generated with web3j version 1.4.1.
 */
class InDive : Contract {
    @Deprecated("")
    protected constructor(
        contractAddress: String?,
        web3j: Web3j?,
        credentials: Credentials?,
        gasPrice: BigInteger?,
        gasLimit: BigInteger?
    ) : super(
        BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit
    ) {
    }

    protected constructor(
        contractAddress: String?,
        web3j: Web3j?,
        credentials: Credentials?,
        contractGasProvider: ContractGasProvider?
    ) : super(
        BINARY, contractAddress, web3j, credentials, contractGasProvider
    ) {
    }

    @Deprecated("")
    protected constructor(
        contractAddress: String?,
        web3j: Web3j?,
        transactionManager: TransactionManager?,
        gasPrice: BigInteger?,
        gasLimit: BigInteger?
    ) : super(
        BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit
    ) {
    }

    protected constructor(
        contractAddress: String?,
        web3j: Web3j?,
        transactionManager: TransactionManager?,
        contractGasProvider: ContractGasProvider?
    ) : super(
        BINARY, contractAddress, web3j, transactionManager, contractGasProvider
    ) {
    }

    fun donate(
        _to: String?,
        _value: BigInteger?,
        _message: String?,
        _time: BigInteger?
    ): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_DONATE,
            Arrays.asList<Type<*>>(
                Address(160, _to),
                Uint256(_value),
                Utf8String(_message),
                Uint256(_time)
            ), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun getDonationEventEvents(transactionReceipt: TransactionReceipt?): List<DonationEventEventResponse> {
        val valueList = extractEventParametersWithLog(DONATIONEVENT_EVENT, transactionReceipt)
        val responses = ArrayList<DonationEventEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = DonationEventEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.artist = eventValues.indexedValues[0].value as String
            typedResponse.donator = eventValues.indexedValues[1].value as String
            typedResponse.value = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse.message = eventValues.nonIndexedValues[1].value as String
            typedResponse.time = eventValues.nonIndexedValues[2].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun donationEventEventFlowable(filter: EthFilter?): Flowable<DonationEventEventResponse> {
        return web3j.ethLogFlowable(filter)
            .map(object : Function<Log?, DonationEventEventResponse> {
                override fun apply(log: Log): DonationEventEventResponse {
                    val eventValues = extractEventParametersWithLog(DONATIONEVENT_EVENT, log)
                    val typedResponse = DonationEventEventResponse()
                    typedResponse.log = log
                    typedResponse.artist = eventValues.indexedValues[0].value as String
                    typedResponse.donator = eventValues.indexedValues[1].value as String
                    typedResponse.value = eventValues.nonIndexedValues[0].value as BigInteger
                    typedResponse.message = eventValues.nonIndexedValues[1].value as String
                    typedResponse.time = eventValues.nonIndexedValues[2].value as BigInteger
                    return typedResponse
                }
            })
    }

    fun donationEventEventFlowable(
        startBlock: DefaultBlockParameter?,
        endBlock: DefaultBlockParameter?
    ): Flowable<DonationEventEventResponse> {
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(DONATIONEVENT_EVENT))
        return donationEventEventFlowable(filter)
    }

    fun setTokenContract(contractAddress: String?): RemoteFunctionCall<TransactionReceipt> {
        val function = Function(
            FUNC_SETTOKENCONTRACT,
            Arrays.asList<Type<*>>(Address(160, contractAddress)), emptyList()
        )
        return executeRemoteCallTransaction(function)
    }

    fun donationHistory(
        param0: String?,
        param1: BigInteger?
    ): RemoteFunctionCall<Tuple5<String, String, BigInteger, String, BigInteger>> {
        val function = Function(
            FUNC_DONATIONHISTORY,
            Arrays.asList<Type<*>>(
                Address(160, param0),
                Uint256(param1)
            ),
            Arrays.asList<TypeReference<*>>(
                object : TypeReference<Address?>() {},
                object : TypeReference<Utf8String?>() {},
                object : TypeReference<Uint256?>() {},
                object : TypeReference<Utf8String?>() {},
                object : TypeReference<Uint256?>() {})
        )
        return RemoteFunctionCall(
            function
        ) {
            val results = executeCallMultipleValueReturn(function)
            Tuple5(
                results[0].value as String,
                results[1].value as String,
                results[2].value as BigInteger,
                results[3].value as String,
                results[4].value as BigInteger
            )
        }
    }

    fun donatorList(
        param0: String?,
        param1: BigInteger?
    ): RemoteFunctionCall<Tuple2<String, BigInteger>> {
        val function = Function(
            FUNC_DONATORLIST,
            Arrays.asList<Type<*>>(
                Address(160, param0),
                Uint256(param1)
            ),
            Arrays.asList<TypeReference<*>>(
                object : TypeReference<Address?>() {},
                object : TypeReference<Uint256?>() {})
        )
        return RemoteFunctionCall(
            function
        ) {
            val results = executeCallMultipleValueReturn(function)
            Tuple2(
                results[0].value as String,
                results[1].value as BigInteger
            )
        }
    }

    fun getDonationHistoryList(artist: String?): RemoteFunctionCall<String> {
        val function = Function(
            FUNC_GETDONATIONHISTORYLIST,
            Arrays.asList<Type<*>>(Address(160, artist)),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Utf8String?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun getDonatorList(artist: String?): RemoteFunctionCall<String> {
        val function = Function(
            FUNC_GETDONATORLIST,
            Arrays.asList<Type<*>>(Address(160, artist)),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Utf8String?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun isDonated(param0: String?, param1: String?): RemoteFunctionCall<Boolean> {
        val function = Function(
            FUNC_ISDONATED,
            Arrays.asList<Type<*>>(
                Address(160, param0),
                Address(160, param1)
            ),
            Arrays.asList<TypeReference<*>>(object : TypeReference<Bool?>() {})
        )
        return executeRemoteCallSingleValueReturn(function, Boolean::class.java)
    }

    class DonationEventEventResponse : BaseEventResponse() {
        var artist: String? = null
        var donator: String? = null
        var value: BigInteger? = null
        var message: String? = null
        var time: BigInteger? = null
    }

    companion object {
        const val BINARY = "Bin file was not provided"
        const val FUNC_DONATE = "donate"
        const val FUNC_SETTOKENCONTRACT = "setTokenContract"
        const val FUNC_DONATIONHISTORY = "donationHistory"
        const val FUNC_DONATORLIST = "donatorList"
        const val FUNC_GETDONATIONHISTORYLIST = "getDonationHistoryList"
        const val FUNC_GETDONATORLIST = "getDonatorList"
        const val FUNC_ISDONATED = "isDonated"
        val DONATIONEVENT_EVENT = Event(
            "DonationEvent",
            Arrays.asList<TypeReference<*>>(
                object : TypeReference<Address?>(true) {},
                object : TypeReference<Address?>(true) {},
                object : TypeReference<Uint256?>() {},
                object : TypeReference<Utf8String?>() {},
                object : TypeReference<Uint256?>() {})
        )

        @Deprecated("")
        fun load(
            contractAddress: String?,
            web3j: Web3j?,
            credentials: Credentials?,
            gasPrice: BigInteger?,
            gasLimit: BigInteger?
        ): InDive {
            return InDive(contractAddress, web3j, credentials, gasPrice, gasLimit)
        }

        @Deprecated("")
        fun load(
            contractAddress: String?,
            web3j: Web3j?,
            transactionManager: TransactionManager?,
            gasPrice: BigInteger?,
            gasLimit: BigInteger?
        ): InDive {
            return InDive(contractAddress, web3j, transactionManager, gasPrice, gasLimit)
        }

        fun load(
            contractAddress: String?,
            web3j: Web3j?,
            credentials: Credentials?,
            contractGasProvider: ContractGasProvider?
        ): InDive {
            return InDive(contractAddress, web3j, credentials, contractGasProvider)
        }

        fun load(
            contractAddress: String?,
            web3j: Web3j?,
            transactionManager: TransactionManager?,
            contractGasProvider: ContractGasProvider?
        ): InDive {
            return InDive(contractAddress, web3j, transactionManager, contractGasProvider)
        }
    }
}